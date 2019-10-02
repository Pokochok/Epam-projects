package by.epam.touragency.command.impl;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.TO_BOOKING_PAGE_PATH;

@Controller
public class ToBookingCommand {
    @PostMapping("/to_booking")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(TO_BOOKING_PAGE_PATH));
    }
}
