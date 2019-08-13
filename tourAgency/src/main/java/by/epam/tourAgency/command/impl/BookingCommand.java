package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.BookingLogic;
import by.epam.tourAgency.resource.ConfigurationManager;
import by.epam.tourAgency.resource.MessageManager;
import by.epam.tourAgency.util.ParameterConstant;
import by.epam.tourAgency.util.Validation;
import static by.epam.tourAgency.util.ParameterConstant.*;
import static by.epam.tourAgency.util.PageMsgConstant.*;

import java.util.Locale;

public class BookingCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        String ticketId = content.getParameter(PARAM_NAME_TICKET_ID);
        String clientId = content.getParameter(PARAM_NAME_CLIENT_ID);
        String clientEmail = content.getParameter(PARAM_NAME_CLIENT_EMAIL);
        String agentId = content.getParameter(PARAM_NAME_AGENT_ID);
        String tourId = content.getParameter(PARAM_NAME_TOUR_ID);

        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();
        try {
            if (!Validation.validateId(clientId) && (!Validation.validateEmail(clientEmail) || !BookingLogic.isClientExists(clientEmail))) {
                content.setAttribute(ParameterConstant.ATTR_NAME_ERROR_EMAIL_NOT_EXISTS,
                        MessageManager.getProperty(CLIENT_EMAIL_NOT_EXISTS_MSG_KEY, new Locale(language)));
                return ConfigurationManager.getProperty(BOOKING_PAGE_PATH);
            }
            page = ConfigurationManager.getProperty(INF_PAGE_FLAG);

            if (BookingLogic.addOrder(tourId, ticketId, clientId, clientEmail, agentId)) {
                content.setAttribute(ATTR_NAME_MSG_KEY, SUCCESSFUL_MSG_KEY);
                LOGGER.debug("New order was creating");
            } else {
                LOGGER.debug("Not successful order creating");
                content.setAttribute(ATTR_NAME_MSG_KEY, NOT_SUCCESSFUL_MSG_KEY);
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
