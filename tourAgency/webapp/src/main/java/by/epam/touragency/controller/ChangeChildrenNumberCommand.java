package by.epam.touragency.controller;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChangeChildrenNumberCommand {
    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Autowired
    private Validation validation;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_children_number")
    public ModelAndView execute(
            @RequestParam(ParameterConstant.PARAM_NAME_NEW_CHILDREN_NUMBER) String newChildrenNumberStr,
            @RequestParam(ParameterConstant.PARAM_NAME_TOUR_ID) String tourIdStr,
            Tour tour
    ) {
        ModelAndView modelAndView = new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(tourIdStr) || !validation.validateNumberOfPeople(newChildrenNumberStr)) {
            modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
            return modelAndView;
        }

        int tourId = Integer.parseInt(tourIdStr);
        int newChildrenNumber = Integer.parseInt(newChildrenNumberStr);
        updateTourLogic.updateChildrenNumber(newChildrenNumber, tourId, tour);
        modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
        modelAndView.addObject(ParameterConstant.ATTR_NAME_CHILDREN_NUMBER, newChildrenNumber);
        return modelAndView;
    }
}
