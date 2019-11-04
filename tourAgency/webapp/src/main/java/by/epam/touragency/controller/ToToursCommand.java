package by.epam.touragency.controller;

import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.Tour;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.logic.ToPageWithListLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@PreAuthorize("permitAll()")
public class ToToursCommand {
    @Autowired
    private ToPageWithListLogic toPageWithListLogic;

    @RequestMapping("/to_tours")
    public ModelAndView execute(@RequestParam(value = ParameterConstant.ATTR_NAME_INDEX, required = false) String index,
                                @RequestParam(value = ParameterConstant.ATTR_NAME_CHANGE_PAGE, required = false) String toChangePage) {
        ModelAndView modelAndView = new ModelAndView();
        int newIndex;
        String userRole = Role.GUEST.toString();
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userRole = userDetails.getUserRole().toString();
        }

        if (index == null) {
            newIndex = 1;
        } else {
            newIndex = (int) (Double.parseDouble(index) + Integer.parseInt(toChangePage));
        }

        Set<Tour> tourSet = null;
        tourSet = toPageWithListLogic.getTourSet(userRole);

        modelAndView.addObject(ParameterConstant.ATTR_NAME_TOURS_PER_PAGE, ParameterConstant.NUMBER_TOURS_PER_PAGE);
        modelAndView.addObject(ParameterConstant.ATTR_NAME_START_TOURS_INDEX, (newIndex - 1) * ParameterConstant.NUMBER_TOURS_PER_PAGE);
        modelAndView.addObject(ParameterConstant.ATTR_NAME_INDEX, newIndex);
        modelAndView.addObject(ParameterConstant.ATTR_NAME_TOUR_LIST, tourSet);
        modelAndView.addObject(ParameterConstant.ATTR_NAME_NUMBER_OF_TOURS, tourSet.size());
        modelAndView.setViewName(ConfigurationManager.getProperty(PageMsgConstant.TO_TOURS_PAGE_PATH));
        return modelAndView;
    }
}
