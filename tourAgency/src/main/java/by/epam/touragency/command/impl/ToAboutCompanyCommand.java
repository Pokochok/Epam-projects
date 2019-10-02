package by.epam.touragency.command.impl;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.ABOUT_US_PAGE_PATH;

@Controller
public class ToAboutCompanyCommand {
    @GetMapping("/to_about_company")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(ABOUT_US_PAGE_PATH));
    }
}
