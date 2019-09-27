package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.OrderChangeLogic;
import by.epam.touragency.util.Validation;

import static by.epam.touragency.util.ParameterConstant.PARAM_NAME_ORDER_ID;

public class RemoveOrderCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        if (!Validation.validateId(content.getParameter(PARAM_NAME_ORDER_ID))){
            return new ToOrdersCommand().execute(content);
        }
        int orderId = Integer.parseInt(content.getParameter(PARAM_NAME_ORDER_ID));
        try {
            OrderChangeLogic.removeOrder(orderId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return new ToOrdersCommand().execute(content);
    }
}
