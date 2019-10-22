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

import static by.epam.touragency.util.PageMsgConstant.TOUR_NAME_EXISTS_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeTourNameCommand {
    @Autowired
    private MessageManager messageManager;

    @Autowired
    private Validation validation;

    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_tour_name")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_NEW_TOUR_NAME) String newTourName,
            @RequestParam(value = PARAM_NAME_TOUR_NAME) String tourName,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE, required = false) Locale language,
            @RequestParam(value = PARAM_NAME_TOUR_ID) String id
    ) throws ControllerException {
        if (language == null){
            language = new Locale(EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(id)|| !validation.validateTourStringItems(tourName) ||
                !validation.validateTourStringItems(newTourName) ) {
            return modelAndView;
        }
        int tourId = Integer.parseInt(id);
        try {
            if (updateTourLogic.updateTourName(newTourName, tourId)) {
                modelAndView.addObject(ATTR_NAME_TOUR_NAME, newTourName);
            } else {
                modelAndView.addObject(ATTR_NAME_TOUR_NAME, tourName);
                modelAndView.addObject(ATTR_NAME_ERROR_TOUR_NAME_EXISTS_MSG,
                        messageManager.getProperty(TOUR_NAME_EXISTS_MSG_KEY, language));
            }
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
        return modelAndView;
    }
}
