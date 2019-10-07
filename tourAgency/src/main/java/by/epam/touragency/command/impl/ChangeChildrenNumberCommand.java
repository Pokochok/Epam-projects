package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.Validation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeChildrenNumberCommand {
    @Secured("ROLE_ADMIN")
    @PostMapping("/change_children_number")
    public ModelAndView execute(
            @RequestParam(PARAM_NAME_NEW_CHILDREN_NUMBER) String newChildrenNumberStr,
            @RequestParam(PARAM_NAME_TOUR_ID) String tourIdStr
    ) throws CommandException {
        ModelAndView modelAndView = new ModelAndView();
        if (!Validation.validateNumberOfPeople(newChildrenNumberStr) ||
                !Validation.validateId(tourIdStr)){
            modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        }

        int tourId = Integer.parseInt(tourIdStr);
        int newChildrenNumber = Integer.parseInt(newChildrenNumberStr);
        try {
            UpdateTourLogic.updateChildrenNumber(newChildrenNumber, tourId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        modelAndView.addObject(ATTR_NAME_CHILDREN_NUMBER, newChildrenNumber);
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        return modelAndView;
    }
}
