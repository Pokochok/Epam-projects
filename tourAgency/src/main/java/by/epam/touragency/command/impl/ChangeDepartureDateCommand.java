package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.logic.UpdateUserLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;
import static by.epam.touragency.util.PageMsgConstant.DATE_ERROR_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeDepartureDateCommand {
    @Autowired
    private Validation validation;

    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Autowired
    private MessageManager messageManager;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_departure_date")
    public ModelAndView execute(
            @RequestParam(PARAM_NAME_NEW_DEPARTURE_DATE) String newDepartureDateStr,
            @RequestParam(PARAM_NAME_ARRIVAL_DATE) String arrivalDateStr,
            @RequestParam(value = PARAM_NAME_TOUR_ID) String tourIdStr,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE, required = false) Locale language
    ) throws CommandException {
        if (language == null){
            language = new Locale(EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(tourIdStr)){
            return modelAndView;
        }
        int tourId = Integer.parseInt(tourIdStr);
        long  newDepartureDate = validation.validateDate(newDepartureDateStr);
        long  arrivalDate = validation.validateDate(arrivalDateStr);
        boolean isValidDate = true;
        if (newDepartureDate == -1 || arrivalDate == -1){
            isValidDate = false;
            LOGGER.debug("Error in date parsing");
        }

        try {
            if (isValidDate && newDepartureDate < arrivalDate && new Date().before(new Date(newDepartureDate))) {
                updateTourLogic.updateDepartureDate(newDepartureDate, tourId);
                modelAndView.addObject(ATTR_NAME_DEPARTURE_DATE, validation.dateToFormat(newDepartureDate));
            } else {
                LOGGER.debug("Invalid date entered");
                modelAndView.addObject(ATTR_NAME_ERROR_DATE,
                        messageManager.getProperty(DATE_ERROR_MSG_KEY, language));
            }
        }catch (LogicException e){
            throw new CommandException(e);
        }
        return modelAndView;
    }
}
