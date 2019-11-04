package by.epam.touragency.controller;

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
            @RequestParam(value = ParameterConstant.PARAM_NAME_NEW_TOUR_NAME) String newTourName,
            @RequestParam(value = ParameterConstant.PARAM_NAME_TOUR_NAME) String tourName,
            @SessionAttribute(value = ParameterConstant.ATTR_NAME_LANGUAGE, required = false) Locale language,
            @RequestParam(value = ParameterConstant.PARAM_NAME_TOUR_ID) String id
    ) {
        if (language == null) {
            language = new Locale(ParameterConstant.EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(id) || !validation.validateTourStringItems(tourName) ||
                !validation.validateTourStringItems(newTourName)) {
            return modelAndView;
        }
        int tourId = Integer.parseInt(id);
        if (updateTourLogic.updateTourName(newTourName, tourId)) {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_TOUR_NAME, newTourName);
        } else {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_TOUR_NAME, tourName);
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_TOUR_NAME_EXISTS_MSG,
                    messageManager.getProperty(PageMsgConstant.TOUR_NAME_EXISTS_MSG_KEY, language));
        }
        return modelAndView;
    }
}
