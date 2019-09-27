package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.OrderChangeLogic;
import by.epam.touragency.util.Validation;

import static by.epam.touragency.util.ParameterConstant.ATTR_NAME_PAYMENT_STATE;
import static by.epam.touragency.util.ParameterConstant.PARAM_NAME_ORDER_ID;

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
