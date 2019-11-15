package by.epam.touragency.controller;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import by.epam.touragency.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Locale;

@Controller
public class ChangeDepartureDateCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private Validation validation;

    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Autowired
    private MessageManager messageManager;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_departure_date")
    public ModelAndView execute(
            @RequestParam(ParameterConstant.PARAM_NAME_NEW_DEPARTURE_DATE) String newDepartureDateStr,
            @RequestParam(ParameterConstant.PARAM_NAME_ARRIVAL_DATE) long arrivalDate,
            @RequestParam(value = ParameterConstant.PARAM_NAME_TOUR_ID) String tourIdStr,
            @SessionAttribute(value = ParameterConstant.ATTR_NAME_LANGUAGE, required = false) Locale language,
            Tour tour
    ) {
        if (language == null) {
            language = new Locale(ParameterConstant.EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(tourIdStr)) {
            return modelAndView;
        }
        int tourId = Integer.parseInt(tourIdStr);
        long newDepartureDate = validation.validateDate(newDepartureDateStr);
        boolean isValidDate = true;
        if (newDepartureDate == -1) {
            isValidDate = false;
            LOGGER.debug("Error in date parsing");
        }

        if (isValidDate && newDepartureDate < arrivalDate && new Date().before(new Date(newDepartureDate))) {
            updateTourLogic.updateDepartureDate(newDepartureDate, tourId, tour);
            modelAndView.addObject(ParameterConstant.ATTR_NAME_DEPARTURE_DATE, newDepartureDate);
        } else {
            LOGGER.debug("Invalid date entered");
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_DATE,
                    messageManager.getProperty(PageMsgConstant.DATE_ERROR_MSG_KEY, language));
        }
        modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
        return modelAndView;
    }
}
