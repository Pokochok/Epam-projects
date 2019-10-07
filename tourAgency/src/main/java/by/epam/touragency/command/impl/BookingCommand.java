package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.BookingLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.ParameterConstant;
import by.epam.touragency.util.Validation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.ParameterConstant.*;
import static by.epam.touragency.util.PageMsgConstant.*;

import java.util.Locale;

@Controller
public class BookingCommand {
    @Secured({ "ROLE_AGENT", "ROLE_CLIENT" })
    @PostMapping("/booking")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_TICKET_ID, required = false) String ticketId,
            @RequestParam(value = PARAM_NAME_CLIENT_ID, required = false) String clientId,
            @RequestParam(value = PARAM_NAME_CLIENT_EMAIL, required = false) String clientEmail,
            @RequestParam(value = PARAM_NAME_AGENT_ID, required = false) String agentId,
            @RequestParam(value = PARAM_NAME_TOUR_ID, required = false) String tourId,
            @RequestParam(value = ATTR_NAME_LANGUAGE, required = false) String language) throws CommandException {
        String page = null;
        if (language == null){
            language = EN_LOCALE;
        }
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (!Validation.validateId(clientId) && (!Validation.validateEmail(clientEmail) || !BookingLogic.isClientExists(clientEmail))) {
                modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_EMAIL_NOT_EXISTS,
                        MessageManager.getProperty(CLIENT_EMAIL_NOT_EXISTS_MSG_KEY, new Locale(language)));
                modelAndView.setViewName(ConfigurationManager.getProperty(BOOKING_PAGE_PATH));
                return modelAndView;
            }

            page = ConfigurationManager.getProperty(INF_PAGE_FLAG);
            if (BookingLogic.addOrder(tourId, ticketId, clientId, clientEmail, agentId)) {
                modelAndView.addObject(ATTR_NAME_MSG_KEY, SUCCESSFUL_MSG_KEY);
                LOGGER.debug("New order was creating");
            } else {
                LOGGER.debug("Not successful order creating");
                modelAndView.addObject(ATTR_NAME_MSG_KEY, NOT_SUCCESSFUL_MSG_KEY);
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        modelAndView.setViewName(page);
        return new ToInfCommand().execute(modelAndView);
    }
}
