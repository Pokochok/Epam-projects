package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.HOME_PAGE_PATH;
import static by.epam.touragency.util.PageMsgConstant.MAIN_PAGE_PATH;

@Controller
@PreAuthorize("permitAll()")
public class BackToMainCommand {
    @RequestMapping(value = {"/back_to_main", "/home"})
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(HOME_PAGE_PATH));
    }
}
