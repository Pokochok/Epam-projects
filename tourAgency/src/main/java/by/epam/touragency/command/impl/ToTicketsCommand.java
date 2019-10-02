package by.epam.touragency.command.impl;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.ToPageWithListLogic;
import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.TO_FLIGHTS_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ToTicketsCommand{
    @RequestMapping("/to_tickets")
    public ModelAndView execute(
            @RequestParam(value = ATTR_NAME_INDEX, required = false) String index,
            @RequestParam(value = ATTR_NAME_CHANGE_PAGE, required = false) String toChangePage
    ) throws CommandException {
        ModelAndView modelAndView = new ModelAndView();
        int newIndex;
        if (index == null) {
            newIndex = 1;
        } else {
            newIndex = (int) (Double.parseDouble(index) + Integer.parseInt(toChangePage));
        }

        Set<Ticket> ticketSet = null;
        try {
            ticketSet = ToPageWithListLogic.getTicketSet();
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        modelAndView.addObject(ATTR_NAME_TICKETS_PER_PAGE, NUMBER_TICKETS_PER_PAGE);
        modelAndView.addObject(ATTR_NAME_START_TICKET_INDEX, (newIndex - 1) * NUMBER_TICKETS_PER_PAGE);
        modelAndView.addObject(ATTR_NAME_INDEX, newIndex);
        modelAndView.addObject(ATTR_NAME_TICKET_LIST, ticketSet);
        modelAndView.addObject(ATTR_NAME_NUMBER_OF_TICKETS, ticketSet.size());
        modelAndView.setViewName(ConfigurationManager.getProperty(TO_FLIGHTS_PAGE_PATH));
        return modelAndView;
    }
}
