package by.epam.touragency.command.impl;

import by.epam.touragency.entity.Tour;
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

import static by.epam.touragency.util.PageMsgConstant.TOUR_NAME_EXISTS_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeTourNameCommand {
    @PostMapping("/change_tour_name")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_NEW_TOUR_NAME) String newTourName,
            @RequestParam(value = PARAM_NAME_TOUR_NAME) String tourName,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE) Locale language,
            @RequestParam(value = PARAM_NAME_TOUR_ID) String id
    ) throws CommandException {
        if (language == null){
            language = new Locale(EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView();
        if (!Validation.validateTourStringItems(newTourName) || !Validation.validateTourStringItems(tourName) ||
                !Validation.validateId(id)) {
            modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
            return modelAndView;
        }
        int tourId = Integer.parseInt(id);
        try {
            if (UpdateTourLogic.updateTourName(newTourName, tourId)) {
                modelAndView.addObject(ATTR_NAME_TOUR_NAME, newTourName);
            } else {
                modelAndView.addObject(ATTR_NAME_TOUR_NAME, tourName);
                modelAndView.addObject(ATTR_NAME_ERROR_TOUR_NAME_EXISTS_MSG,
                        MessageManager.getProperty(TOUR_NAME_EXISTS_MSG_KEY, language));
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        return modelAndView;
    }
}
