package by.epam.touragency.controller;

import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.logic.BookingLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import by.epam.touragency.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


import java.util.Locale;

@Controller
public class BookingCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private Validation validation;

    @Autowired
    private BookingLogic bookingLogic;

    @Secured({"ROLE_AGENT", "ROLE_CLIENT"})
    @PostMapping("/booking")
    public ModelAndView execute(
            @RequestParam(value = ParameterConstant.PARAM_NAME_TICKET_ID, required = false) String ticketId,
            @RequestParam(value = ParameterConstant.PARAM_NAME_CLIENT_EMAIL, required = false) String clientEmail,
            @RequestParam(value = ParameterConstant.PARAM_NAME_TOUR_ID, required = false) String tourId,
            @SessionAttribute(value = ParameterConstant.ATTR_NAME_LANGUAGE, required = false) String language)  {
        String page = null;
        if (language == null) {
            language = ParameterConstant.EN_LOCALE;
        }
        String clientId = null;
        String agentId = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userRole = userDetails.getUserRole().toString();
            if (Role.CLIENT.toString().equals(userRole)) {
                clientId = String.valueOf(userDetails.getUserId());
            } else if (Role.AGENT.toString().equals(userRole)) {
                agentId = String.valueOf(userDetails.getUserId());
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        if (!validation.validateId(clientId) && !bookingLogic.isClientExists(clientEmail)) {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_EMAIL_NOT_EXISTS,
                    messageManager.getProperty(PageMsgConstant.CLIENT_EMAIL_NOT_EXISTS_MSG_KEY, new Locale(language)));
            modelAndView.setViewName(ConfigurationManager.getProperty(PageMsgConstant.BOOKING_PAGE_PATH));
            return modelAndView;
        }

        String msg = null;
        if (bookingLogic.addOrder(tourId, ticketId, clientId, clientEmail, agentId)) {
            msg = PageMsgConstant.SUCCESSFUL_MSG_KEY;
            LOGGER.debug("New order was creating");
        } else {
            LOGGER.debug("Not successful order creating");
            msg = PageMsgConstant.NOT_SUCCESSFUL_MSG_KEY;
        }
        page = "redirect:" + ConfigurationManager.getProperty(PageMsgConstant.INF_URL_PATH) + "?" + ParameterConstant.ATTR_NAME_MSG_KEY + "=" + msg;
        modelAndView.setViewName(page);
        return modelAndView;
    }
}