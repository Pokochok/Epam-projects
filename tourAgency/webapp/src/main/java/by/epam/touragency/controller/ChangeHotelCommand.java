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
public class ChangeHotelCommand {
    @Autowired
    Validation validation;

    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_hotel")
    public ModelAndView execute(
            @RequestParam(ParameterConstant.PARAM_NAME_NEW_HOTEL) String newHotel,
            @RequestParam(ParameterConstant.PARAM_NAME_TOUR_ID) String tourIdStr,
            Tour tour
    ) {
        ModelAndView modelAndView = new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateTourStringItems(newHotel) || !validation.validateId(tourIdStr)) {
            modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
            return modelAndView;
        }

        int tourId = Integer.parseInt(tourIdStr);
        updateTourLogic.updateHotel(newHotel, tourId, tour);
        modelAndView.addObject(ParameterConstant.PARAM_NAME_TOUR_INSTANCE, tour);
        modelAndView.addObject(ParameterConstant.ATTR_NAME_HOTEL, newHotel);
        return modelAndView;
    }
}
