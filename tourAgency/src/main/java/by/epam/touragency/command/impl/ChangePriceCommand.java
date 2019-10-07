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

import java.math.BigDecimal;

import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangePriceCommand {
    @Secured("ROLE_ADMIN")
    @PostMapping("/change_price")
    public ModelAndView execute(
            @RequestParam(PARAM_NAME_NEW_PRICE) String newPriceStr,
            @RequestParam(PARAM_NAME_TOUR_ID) String tourIdStr
    ) throws CommandException {
        ModelAndView modelAndView = new ModelAndView();
        if (!Validation.validatePrice(newPriceStr) ||
                !Validation.validateId(tourIdStr)){
            modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
            return modelAndView;
        }

        int tourId = Integer.parseInt(tourIdStr);
        BigDecimal newPrice = new BigDecimal(newPriceStr);
        try {
            UpdateTourLogic.updatePrice(newPrice, tourId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        modelAndView.addObject(ATTR_NAME_PRICE, newPrice);
        modelAndView.setViewName(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
        return modelAndView;
    }
}
