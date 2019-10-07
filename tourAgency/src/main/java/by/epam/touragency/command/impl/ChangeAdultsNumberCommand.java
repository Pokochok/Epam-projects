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
public class ChangeAdultsNumberCommand{
    @Secured("ROLE_ADMIN")
    @PostMapping("/change_adults_number")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_NEW_ADULTS_NUMBER) String newAdultsNumberStr,
            @RequestParam(value =PARAM_NAME_TOUR_ID ) String tourIdStr
    ) throws CommandException {
        ModelAndView modelAndView = new ModelAndView();
        if (!Validation.validateNumberOfPeople(newAdultsNumberStr) ||
                !Validation.validateId(tourIdStr)) {
            modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
            return modelAndView;
        }

        int tourId = Integer.parseInt(tourIdStr);
        int newAdultsNumber = Integer.parseInt(newAdultsNumberStr);
        try {
            UpdateTourLogic.updateAdultsNumber(newAdultsNumber, tourId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        modelAndView.addObject(ATTR_NAME_ADULTS_NUMBER, newAdultsNumber);
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        return modelAndView;
    }
}
