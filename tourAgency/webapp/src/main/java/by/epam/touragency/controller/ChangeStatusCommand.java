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

import java.util.Objects;

@Controller
public class ChangeStatusCommand {
    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Autowired
    private Validation validation;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_status")
    public ModelAndView execute(
            @RequestParam(ParameterConstant.ATTR_NAME_STATUS) String status,
            @RequestParam(ParameterConstant.PARAM_NAME_TOUR_ID) String touridStr,
            Tour tour
    ) {
        status = Objects.equals(status, "AVAILABLE") ? "NOT_AVAILABLE" : "AVAILABLE";
        ModelAndView modelAndView = new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(touridStr)) {
            modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
            return modelAndView;
        }
        int tourId = Integer.parseInt(touridStr);
        updateTourLogic.updateStatus(status, tourId, tour);
        modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
        modelAndView.addObject(ParameterConstant.ATTR_NAME_STATUS, status);
        return modelAndView;
    }
}
