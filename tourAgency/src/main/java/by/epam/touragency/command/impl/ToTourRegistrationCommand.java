package by.epam.touragency.command.impl;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.TO_TOUR_REGISTRATION_PAGE_PATH;

@Controller
public class ToTourRegistrationCommand {
    @Secured("ROLE_ADMIN")
    @GetMapping("/to_tour_registration")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH));
    }
}
