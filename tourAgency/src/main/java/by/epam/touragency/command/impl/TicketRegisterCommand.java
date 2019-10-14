package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.TicketRegistrationLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class TicketRegisterCommand {
    @Autowired
    private TicketRegistrationLogic ticketRegistrationLogic;

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private Validation validation;

    @Secured("ROLE_ADMIN")
    @PostMapping("/ticket_register_command")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_FLIGHT_NUMBER) String flightNumber,
            @RequestParam(value = PARAM_NAME_TICKET_NUMBER) String ticketNumber,
            @RequestParam(value = PARAM_NAME_DEPARTURE_CITY) String departureCity,
            @RequestParam(value = PARAM_NAME_ARRIVAL_CITY) String arrivalCity,
            @RequestParam(value = PARAM_NAME_DEPARTURE_DATE) String departureDateStr,
            @RequestParam(value = PARAM_NAME_ARRIVAL_DATE) String arrivalDateStr,
            @RequestParam(value = ATTR_NAME_LANGUAGE, required = false) String language
    ) throws CommandException {
        ModelAndView modelAndView = new ModelAndView();
        String page = null;
        boolean isValid = true;
        if (language == null){
            language = EN_LOCALE;
        }

        long departureDate = validation.validateDate(departureDateStr);
        long arrivalDate = validation.validateDate(arrivalDateStr);

        if(!new Date().before(new Date(departureDate)) || !new Date(departureDate).before(new Date(arrivalDate))){
            LOGGER.debug("Invalid date entered");
            modelAndView.addObject(ATTR_NAME_ERROR_DATE,
                    messageManager.getProperty(DATE_ERROR_MSG_KEY, new Locale(language)));
            modelAndView.setViewName(ConfigurationManager.getProperty(TICKET_REGISTRATION_PAGE_PATH));
            return modelAndView;
        }

        if (!validation.validateTicketNumbers(flightNumber) || !validation.validateTicketNumbers(ticketNumber) ||
                !validation.validateTourStringItems(departureCity) || !validation.validateTourStringItems(arrivalCity)) {
            LOGGER.debug("Invalid ticket data");
            isValid = false;
        }

        try {
            if (isValid && ticketRegistrationLogic.isTicketExists(flightNumber, ticketNumber, departureCity,
                    arrivalCity, departureDate, arrivalDate)) {
                LOGGER.debug("Ticket is exists");
                isValid = false;
            }
            if (isValid) {
                ticketRegistrationLogic.addTicket(flightNumber, ticketNumber, departureCity,
                        arrivalCity, departureDate, arrivalDate);
                modelAndView.addObject(ATTR_NAME_MSG_KEY, REGISTRATION_SUCCESS_MSG_KEY);
                page = ConfigurationManager.getProperty(TO_INF_PAGE_PATH);
            } else {
                modelAndView.addObject(ATTR_NAME_MSG_KEY, REGISTRATION_NOT_SUCCESS_MSG_KEY);
                page = ConfigurationManager.getProperty(TO_INF_PAGE_PATH);
            }
        }catch (LogicException e){
            throw new CommandException(e);
        }
        modelAndView.setViewName(page);
        return modelAndView;
    }
}