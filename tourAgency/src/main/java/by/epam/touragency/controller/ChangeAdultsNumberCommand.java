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

import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeAdultsNumberCommand{
    @Autowired
    Validation validation;

    @Autowired
    UpdateTourLogic updateTourLogic;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_adults_number")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_NEW_ADULTS_NUMBER) String newAdultsNumberStr,
            @RequestParam(value =PARAM_NAME_TOUR_ID ) String tourIdStr
    ) throws ControllerException {
        ModelAndView modelAndView = new ModelAndView();
        if (!validation.validateNumberOfPeople(newAdultsNumberStr) ||
                !validation.validateId(tourIdStr)) {
            modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
            return modelAndView;
        }

        int tourId = Integer.parseInt(tourIdStr);
        int newAdultsNumber = Integer.parseInt(newAdultsNumberStr);
        try {
            updateTourLogic.updateAdultsNumber(newAdultsNumber, tourId);
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
        modelAndView.addObject(ATTR_NAME_ADULTS_NUMBER, newAdultsNumber);
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        return modelAndView;
    }
}
