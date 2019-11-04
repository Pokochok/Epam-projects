package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.PageMsgConstant;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToRegistrationCommand {
    @Secured({"ROLE_ANONYMOUS"})
    @GetMapping("/to_registration")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.TO_REGISTRATION_PAGE_PATH));
    }
}
