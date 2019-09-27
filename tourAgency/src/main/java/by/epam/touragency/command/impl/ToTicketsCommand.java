package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.ToPageWithListLogic;
import by.epam.touragency.resource.ConfigurationManager;

import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.TO_FLIGHTS_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

public class ToTicketsCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        int newIndex;

        if (content.getParameter(ATTR_NAME_INDEX) == null) {
            newIndex = 1;
        } else {
            newIndex = (int) (Double.parseDouble(content.getParameter(ATTR_NAME_INDEX)) +
                                Integer.parseInt(content.getParameter(ATTR_NAME_CHANGE_PAGE)));
        }

        Set<Ticket> ticketSet = null;
        try {
            ticketSet = ToPageWithListLogic.getTicketSet();
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        content.setAttribute(ATTR_NAME_TICKETS_PER_PAGE, NUMBER_TICKETS_PER_PAGE);
        content.setAttribute(ATTR_NAME_START_TICKET_INDEX, (newIndex - 1) * NUMBER_TICKETS_PER_PAGE);
        content.setAttribute(ATTR_NAME_INDEX, newIndex);
        content.setAttribute(ATTR_NAME_TICKET_LIST, ticketSet);

        content.setAttribute(ATTR_NAME_NUMBER_OF_TICKETS, ticketSet.size());
        return ConfigurationManager.getProperty(TO_FLIGHTS_PAGE_PATH);
    }
}
