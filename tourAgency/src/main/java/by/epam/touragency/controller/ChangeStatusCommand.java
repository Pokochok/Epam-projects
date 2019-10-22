package by.epam.touragency.controller;

import by.epam.touragency.exception.ControllerException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.ATTR_NAME_STATUS;
import static by.epam.touragency.util.ParameterConstant.PARAM_NAME_TOUR_ID;

@Controller
public class ChangeStatusCommand {
    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Autowired
    private Validation validation;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_status")
    public ModelAndView execute(
            @RequestParam(ATTR_NAME_STATUS) String status,
            @RequestParam(PARAM_NAME_TOUR_ID) String touridStr
    ) throws ControllerException {
        status = Objects.equals(status, "AVAILABLE") ? "NOT_AVAILABLE" : "AVAILABLE";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateId(touridStr)) {
            return modelAndView;
        }
        int tourId = Integer.parseInt(touridStr);
        try {
            updateTourLogic.updateStatus(status, tourId);
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
        modelAndView.addObject(ATTR_NAME_STATUS, status);
        return modelAndView;
    }
}
