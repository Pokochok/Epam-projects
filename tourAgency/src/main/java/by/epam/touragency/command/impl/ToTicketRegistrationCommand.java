package by.epam.touragency.command.impl;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.TO_TICKET_REGISTRATION_PAGE_PATH;

@Controller
public class ToTicketRegistrationCommand {
    @GetMapping("/to_ticket_registration")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(TO_TICKET_REGISTRATION_PAGE_PATH));
    }
}
