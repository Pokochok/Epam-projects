package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.PageMsgConstant;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToUserProfileCommand {
    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @GetMapping("/to_user_profile")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.USER_PROFILE_PAGE_PATH));
    }
}
