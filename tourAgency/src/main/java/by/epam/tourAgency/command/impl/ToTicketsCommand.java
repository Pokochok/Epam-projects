package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.entity.Ticket;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.ToPageWithListLogic;
import by.epam.tourAgency.resource.ConfigurationManager;

import java.util.Set;

import static by.epam.tourAgency.util.PageMsgConstant.TO_FLIGHTS_PAGE_PATH;
import static by.epam.tourAgency.util.ParameterConstant.*;

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
