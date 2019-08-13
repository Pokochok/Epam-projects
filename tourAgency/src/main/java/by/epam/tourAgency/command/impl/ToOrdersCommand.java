package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.entity.Order;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.ToPageWithListLogic;
import by.epam.tourAgency.resource.ConfigurationManager;

import java.util.Set;

import static by.epam.tourAgency.util.PageMsgConstant.TO_ORDERS_PAGE_PATH;
import static by.epam.tourAgency.util.ParameterConstant.*;

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
