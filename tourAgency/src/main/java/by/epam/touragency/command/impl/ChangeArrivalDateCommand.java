package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.DATE_ERROR_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.PageMsgConstant.LOGGER;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeArrivalDateCommand {
    @PostMapping("/change_arrival_date")
    public ModelAndView execute(
            @RequestParam(PARAM_NAME_DEPARTURE_DATE) String departureDateStr,
            @RequestParam(PARAM_NAME_NEW_ARRIVAL_DATE) String newArrivalDateStr,
            @RequestParam(value = PARAM_NAME_TOUR_ID) String tourIdStr,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE) Locale language
    ) throws CommandException {
        if (language == null){
            language = new Locale(EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView();
        if (!Validation.validateId(tourIdStr)){
            modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        }
        int tourId = Integer.parseInt(tourIdStr);
        long newArrivalDate = Validation.validateDate(newArrivalDateStr);
        long departureDate = Validation.validateDate(departureDateStr);
        if (newArrivalDate == -1 || departureDate == -1){
            LOGGER.debug("Error in date parsing");
            modelAndView.addObject(ATTR_NAME_ERROR_DATE,
                    MessageManager.getProperty(DATE_ERROR_MSG_KEY, language));
        }

        try {
            if (departureDate < newArrivalDate) {
                UpdateTourLogic.updateArrivalDate(newArrivalDate, tourId);
                modelAndView.addObject(ATTR_NAME_ARRIVAL_DATE, Validation.dateToFormat(newArrivalDate));
            } else {
                modelAndView.addObject(ATTR_NAME_ERROR_DATE,
                        MessageManager.getProperty(DATE_ERROR_MSG_KEY, language));
            }
        }catch (LogicException e){
            throw new CommandException(e);
        }
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        return modelAndView;
    }
}
