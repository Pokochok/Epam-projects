package by.epam.touragency.command.impl;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.ToPageWithListLogic;
import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.TO_TOURS_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
@PreAuthorize("permitAll()")
public class ToToursCommand {
    @RequestMapping("/to_tours")
    public ModelAndView execute(@SessionAttribute(value = ATTR_NAME_USER_ROLE, required = false)String role,
                                @RequestParam(value = ATTR_NAME_INDEX, required = false) String index,
                                @RequestParam(value = ATTR_NAME_CHANGE_PAGE, required = false) String toChangePage) throws CommandException {
        ModelAndView modelAndView = new ModelAndView();
        int newIndex;
//        String userRole = String.valueOf(role);
        String userRole = "CLIENT";

        if (index == null) {
            newIndex = 1;
        } else {
            newIndex = (int) (Double.parseDouble(index) + Integer.parseInt(toChangePage));
        }

        Set<Tour> tourSet = null;
        try {
            tourSet = ToPageWithListLogic.getTourSet(userRole);
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        modelAndView.addObject(ATTR_NAME_TOURS_PER_PAGE, NUMBER_TOURS_PER_PAGE);
        modelAndView.addObject(ATTR_NAME_START_TOURS_INDEX, (newIndex - 1) * NUMBER_TOURS_PER_PAGE);
        modelAndView.addObject(ATTR_NAME_INDEX, newIndex);
        modelAndView.addObject(ATTR_NAME_TOUR_LIST, tourSet);
        modelAndView.addObject(ATTR_NAME_NUMBER_OF_TOURS, tourSet.size());
        modelAndView.setViewName(ConfigurationManager.getProperty(TO_TOURS_PAGE_PATH));
        return modelAndView;
    }
}
