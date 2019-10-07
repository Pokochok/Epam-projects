package by.epam.touragency.command.impl;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.WELCOME_PAGE_PATH;

@Controller
@RequestMapping("/logout")
public class LogoutCommand {
    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @GetMapping
    public ModelAndView execute() {
        String page = ConfigurationManager.getProperty(WELCOME_PAGE_PATH);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(page);
        return modelAndView;
    }
}
