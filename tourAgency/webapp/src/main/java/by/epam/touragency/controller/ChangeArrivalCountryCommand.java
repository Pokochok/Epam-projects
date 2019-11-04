package by.epam.touragency.controller;

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
public class ChangeArrivalCountryCommand {
    @Autowired
    Validation validation;

    @Autowired
    UpdateTourLogic updateTourLogic;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_arrival_country")
    public ModelAndView execute(
            @RequestParam(value = ParameterConstant.PARAM_NAME_NEW_ARRIVAL_COUNTRY) String newArrivalCountry,
            @RequestParam(value = ParameterConstant.PARAM_NAME_TOUR_ID) String tourIdStr
    ) {
        ModelAndView modelAndView = new ModelAndView();
        if (!validation.validateTourStringItems(newArrivalCountry) ||
                !validation.validateId(tourIdStr)) {
            modelAndView.setViewName(ConfigurationManager.getProperty(PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH));
            return modelAndView;
        }

        int tourId = Integer.parseInt(tourIdStr);
        updateTourLogic.updateArrivalCountry(newArrivalCountry, tourId);
        modelAndView.addObject(ParameterConstant.ATTR_NAME_ARRIVAL_COUNTRY, newArrivalCountry);
        modelAndView.setViewName(ConfigurationManager.getProperty(PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH));
        return modelAndView;
    }
}