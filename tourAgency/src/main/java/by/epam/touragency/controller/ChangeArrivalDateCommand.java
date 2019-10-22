package by.epam.touragency.controller;

import by.epam.touragency.exception.ControllerException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateTourLogic;
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

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.DATE_ERROR_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.PageMsgConstant.LOGGER;
import static by.epam.touragency.util.ParameterConstant.*;

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
            @RequestParam(PARAM_NAME_DEPARTURE_DATE) String departureDateStr,
            @RequestParam(PARAM_NAME_NEW_ARRIVAL_DATE) String newArrivalDateStr,
            @RequestParam(value = PARAM_NAME_TOUR_ID) String tourIdStr,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE, required = false) Locale language
    ) throws ControllerException {
        if (language == null){
            language = new Locale(EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(tourIdStr)){
            return modelAndView;
        }
        int tourId = Integer.parseInt(tourIdStr);
        long newArrivalDate = validation.validateDate(newArrivalDateStr);
        long departureDate = validation.validateDate(departureDateStr);
        if (newArrivalDate == -1 || departureDate == -1){
            LOGGER.debug("Error in date parsing");
            modelAndView.addObject(ATTR_NAME_ERROR_DATE,
                    messageManager.getProperty(DATE_ERROR_MSG_KEY, language));
            return modelAndView;
        }

        try {
            if (departureDate < newArrivalDate) {
                updateTourLogic.updateArrivalDate(newArrivalDate, tourId);
                modelAndView.addObject(ATTR_NAME_ARRIVAL_DATE, validation.dateToFormat(newArrivalDate));
            } else {
                modelAndView.addObject(ATTR_NAME_ERROR_DATE,
                        messageManager.getProperty(DATE_ERROR_MSG_KEY, language));
            }
        }catch (LogicException e){
            throw new ControllerException(e);
        }
        return modelAndView;
    }
}
