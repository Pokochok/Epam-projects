package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.USER_PROFILE_PAGE_PATH;

@Controller
public class ToUserProfileCommand {
    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @GetMapping("/to_user_profile")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH));
    }
}
