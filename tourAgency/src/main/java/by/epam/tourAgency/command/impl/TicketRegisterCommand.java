package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.TicketRegistrationLogic;
import by.epam.tourAgency.resource.ConfigurationManager;
import by.epam.tourAgency.resource.MessageManager;
import by.epam.tourAgency.util.Validation;

import java.util.Date;
import java.util.Locale;

import static by.epam.tourAgency.util.PageMsgConstant.*;
import static by.epam.tourAgency.util.ParameterConstant.*;

public class TicketRegisterCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        boolean isValid = true;
        String flightNumber = content.getParameter(PARAM_NAME_FLIGHT_NUMBER);
        String ticketNumber = content.getParameter(PARAM_NAME_TICKET_NUMBER);
        String departureCity = content.getParameter(PARAM_NAME_DEPARTURE_CITY);
        String arrivalCity = content.getParameter(PARAM_NAME_ARRIVAL_CITY);
        String departureDateStr = content.getParameter(PARAM_NAME_DEPARTURE_DATE);
        String arrivalDateStr = content.getParameter(PARAM_NAME_ARRIVAL_DATE);
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString() : content.getLocalName();

        long departureDate = Validation.validateDate(departureDateStr);
        long arrivalDate = Validation.validateDate(arrivalDateStr);

        if(!new Date().before(new Date(departureDate)) || !new Date(departureDate).before(new Date(arrivalDate))){
            LOGGER.debug("Invalid date entered");
            content.setAttribute(ATTR_NAME_ERROR_DATE,
                    MessageManager.getProperty(DATE_ERROR_MSG_KEY, new Locale(language)));
            return ConfigurationManager.getProperty(TICKET_REGISTRATION_PAGE_PATH);
        }

        if (!Validation.validateTicketNumbers(flightNumber) || !Validation.validateTicketNumbers(ticketNumber) ||
                !Validation.validateTourStringItems(departureCity) || !Validation.validateTourStringItems(arrivalCity)) {
            LOGGER.debug("Invalid ticket data");
            isValid = false;
        }

        try {
            if (TicketRegistrationLogic.isTicketExists(flightNumber, ticketNumber, departureCity,
                    arrivalCity, departureDate, arrivalDate)) {
                LOGGER.debug("Ticket is exists");
                isValid = false;
            }
            if (isValid) {
                TicketRegistrationLogic.addTicket(flightNumber, ticketNumber, departureCity,
                        arrivalCity, departureDate, arrivalDate);
                content.setAttribute(ATTR_NAME_MSG_KEY, REGISTRATION_SUCCESS_MSG_KEY);
                page = ConfigurationManager.getProperty(INF_PAGE_FLAG);
            } else {
                content.setAttribute(ATTR_NAME_MSG_KEY, REGISTRATION_NOT_SUCCESS_MSG_KEY);
                page = ConfigurationManager.getProperty(INF_PAGE_FLAG);
            }
        }catch (LogicException e){
            throw new CommandException(e);
        }
        return page;
    }
}