package by.epam.touragency.controller;

import by.epam.touragency.logic.TicketService;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RemoveTicketsController {
    @Autowired
    private TicketService ticketService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/remove_tickets")
    public ModelAndView removeTickets() {
        ticketService.removeInvalidTicketsWithOrders();
        String page = "redirect:" + ConfigurationManager.getProperty(PageMsgConstant.INF_URL_PATH) + "?" + ParameterConstant.ATTR_NAME_MSG_KEY + "=" + PageMsgConstant.OPERATION_SUCCESS_MSG_KEY;
        return new ModelAndView(page);
    }
}
