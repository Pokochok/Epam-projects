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
public class ChangeDepartureCityCommand {
    @Autowired
    private Validation validation;

    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Secured("ROLE_ADMIN")
    @PostMapping("/change_departure_city")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_NEW_DEPARTURE_CITY) String newDepartureCity,
            @RequestParam(value = PARAM_NAME_TOUR_ID) String tourIdStr
    ) throws ControllerException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        if (!validation.validateTourStringItems(newDepartureCity) || !validation.validateId(tourIdStr)) {
            return modelAndView;
        }

        int tourId = Integer.parseInt(tourIdStr);
        try {
            updateTourLogic.updateDepartureCity(newDepartureCity, tourId);
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
        modelAndView.addObject(ATTR_NAME_DEPARTURE_CITY, newDepartureCity);
        return modelAndView;
    }
}
