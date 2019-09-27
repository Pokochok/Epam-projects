package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.entity.Order;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.ToPageWithListLogic;
import by.epam.touragency.resource.ConfigurationManager;

import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.TO_ORDERS_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

public class ToOrdersCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        int newIndex;
        String userRole = String.valueOf(content.getSessionAttribute(ATTR_NAME_USER_ROLE));
        String userId = String.valueOf(content.getSessionAttribute(ATTR_NAME_USER_ID));

        if (content.getParameter(ATTR_NAME_INDEX) == null) {
            newIndex = 1;
        } else {
            newIndex = (int) (Double.parseDouble(content.getParameter(ATTR_NAME_INDEX)) +
                                Integer.parseInt(content.getParameter(ATTR_NAME_CHANGE_PAGE)));
        }

        Set<Order> orderSet = null;
        try {
            orderSet = ToPageWithListLogic.getOrderSet(userRole, userId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        content.setAttribute(ATTR_NAME_ORDERS_PER_PAGE, NUMBER_ORDERS_PER_PAGE);
        content.setAttribute(ATTR_NAME_START_ORDERS_INDEX, (newIndex - 1) * NUMBER_ORDERS_PER_PAGE);
        content.setAttribute(ATTR_NAME_INDEX, newIndex);
        content.setAttribute(ATTR_NAME_ORDER_LIST, orderSet);

        content.setAttribute(ATTR_NAME_NUMBER_OF_ORDERS, orderSet.size());
        return ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH);
    }
}
