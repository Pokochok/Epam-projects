package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.OrderChangeLogic;
import by.epam.tourAgency.util.Validation;

import static by.epam.tourAgency.util.ParameterConstant.ATTR_NAME_PAYMENT_STATE;
import static by.epam.tourAgency.util.ParameterConstant.PARAM_NAME_ORDER_ID;

public class PayOrderCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        if (!Validation.validateId(content.getParameter(PARAM_NAME_ORDER_ID))){
            return new ToOrdersCommand().execute(content);
        }

        int orderId = Integer.parseInt(content.getParameter(PARAM_NAME_ORDER_ID));
        try {
            OrderChangeLogic.payOrder(orderId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        content.setAttribute(ATTR_NAME_PAYMENT_STATE, Boolean.TRUE);
        return new ToOrdersCommand().execute(content);
    }
}
