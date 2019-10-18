package by.epam.touragency.command.impl;

import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.BookingLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.ParameterConstant;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.touragency.util.ParameterConstant.*;
import static by.epam.touragency.util.PageMsgConstant.*;

import java.net.http.HttpResponse;
import java.util.Locale;

@Controller
public class BookingCommand {
    @Autowired
    private MessageManager messageManager;

    @Autowired
    private Validation validation;

    @Autowired
    private BookingLogic bookingLogic;

    @Secured({ "ROLE_AGENT", "ROLE_CLIENT" })
    @PostMapping("/booking")
    public ModelAndView execute(
                                @RequestParam(value = PARAM_NAME_TICKET_ID, required = false) String ticketId,
                                @RequestParam(value = PARAM_NAME_CLIENT_EMAIL, required = false) String clientEmail,
                                @RequestParam(value = PARAM_NAME_TOUR_ID, required = false) String tourId,
                                @RequestParam(value = ATTR_NAME_LANGUAGE, required = false) String language) throws CommandException {
        String page = null;
        if (language == null){
            language = EN_LOCALE;
        }
        String clientId = null;
        String agentId = null;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userRole = userDetails.getUserRole().toString();
            if (Role.CLIENT.toString().equals(userRole)){
                clientId = String.valueOf(userDetails.getUserId());
            } else if (Role.AGENT.toString().equals(userRole)) {
                agentId = String.valueOf(userDetails.getUserId());
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        try {
            if (!validation.validateId(clientId) && !bookingLogic.isClientExists(clientEmail)) {
                modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_EMAIL_NOT_EXISTS,
                        messageManager.getProperty(CLIENT_EMAIL_NOT_EXISTS_MSG_KEY, new Locale(language)));
                modelAndView.setViewName(ConfigurationManager.getProperty(BOOKING_PAGE_PATH));
                return modelAndView;
            }

            String msg = null;
            if (bookingLogic.addOrder(tourId, ticketId, clientId, clientEmail, agentId)) {
                msg = SUCCESSFUL_MSG_KEY;
                LOGGER.debug("New order was creating");
            } else {
                LOGGER.debug("Not successful order creating");
                msg = NOT_SUCCESSFUL_MSG_KEY;
            }
            page = "redirect:"  + ConfigurationManager.getProperty(INF_URL_PATH) + "?" + ATTR_NAME_MSG_KEY + "=" + msg;
            modelAndView.setViewName(page);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return modelAndView;
    }
}