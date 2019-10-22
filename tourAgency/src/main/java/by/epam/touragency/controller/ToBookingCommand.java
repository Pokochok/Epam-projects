package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.TO_BOOKING_PAGE_PATH;

@Controller
public class ToBookingCommand {
    @Secured({"ROLE_AGENT", "ROLE_CLIENT"})
    @PostMapping("/to_booking")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(TO_BOOKING_PAGE_PATH));
    }
}
