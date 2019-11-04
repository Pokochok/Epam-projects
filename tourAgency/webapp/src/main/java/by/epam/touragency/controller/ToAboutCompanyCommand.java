package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.ABOUT_US_PAGE_PATH;

@Controller
@PreAuthorize("isAuthenticated()")
public class ToAboutCompanyCommand {
    @PreAuthorize("permitAll()")
    @GetMapping("/to_about_company")
    @ResponseBody
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(ABOUT_US_PAGE_PATH));
    }
}
