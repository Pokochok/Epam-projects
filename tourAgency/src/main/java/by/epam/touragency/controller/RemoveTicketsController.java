package by.epam.touragency.controller;

import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.logic.TicketService;
import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.ATTR_NAME_MSG_KEY;

@Controller
public class RemoveTicketsController {
    @Autowired
    private TicketService ticketService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/remove_tickets")
    public ModelAndView removeTickets() throws RepositoryException {
        ticketService.removeInvalidTicketsWithOrders();
        String page = "redirect:" + ConfigurationManager.getProperty(INF_URL_PATH) + "?" + ATTR_NAME_MSG_KEY + "=" + OPERATION_SUCCESS_MSG_KEY;
        return new ModelAndView(page);
    }
}
