package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.Validation;

import java.math.BigDecimal;

import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

public class ChangePriceCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        if (!Validation.validatePrice(content.getParameter(PARAM_NAME_NEW_PRICE)) ||
                !Validation.validateId(content.getParameter(PARAM_NAME_TOUR_ID))){
            return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
        }

        int tourId = Integer.parseInt(content.getParameter(PARAM_NAME_TOUR_ID));
        BigDecimal newPrice = new BigDecimal(content.getParameter(PARAM_NAME_NEW_PRICE));
        try {
            UpdateTourLogic.updatePrice(newPrice, tourId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        content.setAttribute(ATTR_NAME_PRICE, newPrice);
        return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
    }
}
