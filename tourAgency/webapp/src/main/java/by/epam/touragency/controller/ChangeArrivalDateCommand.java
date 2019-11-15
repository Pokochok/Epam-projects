package by.epam.touragency.controller;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class ChangeArrivalDateCommand {
    @Autowired
    UpdateTourLogic updateTourLogic;

    @Autowired
    private Validation validation;

    @Autowired
    private MessageManager messageManager;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_arrival_date")
    public ModelAndView execute(
            @RequestParam(ParameterConstant.PARAM_NAME_DEPARTURE_DATE) long departureDate,
            @RequestParam(ParameterConstant.PARAM_NAME_NEW_ARRIVAL_DATE) String newArrivalDateStr,
            @RequestParam(value = ParameterConstant.PARAM_NAME_TOUR_ID) String tourIdStr,
            @SessionAttribute(value = ParameterConstant.ATTR_NAME_LANGUAGE, required = false) Locale language,
            Tour tour
    ) {

        if (language == null) {
            language = new Locale(ParameterConstant.EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(tourIdStr)) {
            modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
            return modelAndView;
        }
        int tourId = Integer.parseInt(tourIdStr);
        long newArrivalDate = validation.validateDate(newArrivalDateStr);
        if (newArrivalDate == -1) {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_DATE,
                    messageManager.getProperty(PageMsgConstant.DATE_ERROR_MSG_KEY, language));
            modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
            return modelAndView;
        }

        if (departureDate < newArrivalDate) {
            updateTourLogic.updateArrivalDate(newArrivalDate, tourId, tour);
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ARRIVAL_DATE, newArrivalDate);
        } else {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_DATE,
                    messageManager.getProperty(PageMsgConstant.DATE_ERROR_MSG_KEY, language));
        }
        modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
        return modelAndView;
    }
}
