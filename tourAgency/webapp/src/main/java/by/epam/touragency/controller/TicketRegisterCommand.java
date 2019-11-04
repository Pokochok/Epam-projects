package by.epam.touragency.controller;

import by.epam.touragency.logic.TicketRegistrationLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import by.epam.touragency.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Locale;

@Controller
public class TicketRegisterCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TicketRegistrationLogic ticketRegistrationLogic;

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private Validation validation;

    @Secured("ROLE_ADMIN")
    @PostMapping("/ticket_register_command")
    public ModelAndView execute(
            @RequestParam(value = ParameterConstant.PARAM_NAME_FLIGHT_NUMBER) String flightNumber,
            @RequestParam(value = ParameterConstant.PARAM_NAME_TICKET_NUMBER) String ticketNumber,
            @RequestParam(value = ParameterConstant.PARAM_NAME_DEPARTURE_CITY) String departureCity,
            @RequestParam(value = ParameterConstant.PARAM_NAME_ARRIVAL_CITY) String arrivalCity,
            @RequestParam(value = ParameterConstant.PARAM_NAME_DEPARTURE_DATE) String departureDateStr,
            @RequestParam(value = ParameterConstant.PARAM_NAME_ARRIVAL_DATE) String arrivalDateStr,
            @SessionAttribute(value = ParameterConstant.ATTR_NAME_LANGUAGE, required = false) String language
    ) {
        ModelAndView modelAndView = new ModelAndView();
        String page = null;
        boolean isValid = true;
        if (language == null) {
            language = ParameterConstant.EN_LOCALE;
        }

        long departureDate = validation.validateDate(departureDateStr);
        long arrivalDate = validation.validateDate(arrivalDateStr);

        if (!new Date().before(new Date(departureDate)) || !new Date(departureDate).before(new Date(arrivalDate))) {
            LOGGER.debug("Invalid date entered");
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_DATE,
                    messageManager.getProperty(PageMsgConstant.DATE_ERROR_MSG_KEY, new Locale(language)));
            modelAndView.setViewName(ConfigurationManager.getProperty(PageMsgConstant.TICKET_REGISTRATION_PAGE_PATH));
            return modelAndView;
        }

        if (!validation.validateTicketNumbers(flightNumber) || !validation.validateTicketNumbers(ticketNumber) ||
                !validation.validateTourStringItems(departureCity) || !validation.validateTourStringItems(arrivalCity)) {
            LOGGER.debug("Invalid ticket data");
            isValid = false;
        }

        if (isValid && ticketRegistrationLogic.isTicketExists(flightNumber, ticketNumber, departureCity,
                arrivalCity, departureDate, arrivalDate)) {
            LOGGER.debug("Ticket is exists");
            isValid = false;
        }
        String msg = null;
        if (isValid) {
            ticketRegistrationLogic.addTicket(flightNumber, ticketNumber, departureCity,
                    arrivalCity, departureDate, arrivalDate);
            msg = PageMsgConstant.REGISTRATION_SUCCESS_MSG_KEY;
            page = "redirect:" + ConfigurationManager.getProperty(PageMsgConstant.INF_URL_PATH) + "?" + ParameterConstant.ATTR_NAME_MSG_KEY + "=" + msg;
        } else {
            msg = PageMsgConstant.REGISTRATION_NOT_SUCCESS_MSG_KEY;
            page = "redirect:" + ConfigurationManager.getProperty(PageMsgConstant.INF_URL_PATH) + "?" + ParameterConstant.ATTR_NAME_MSG_KEY + "=" + msg;
        }
        modelAndView.setViewName(page);
        return modelAndView;
    }
}