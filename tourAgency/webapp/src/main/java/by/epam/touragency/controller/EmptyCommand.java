package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.PageMsgConstant;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("permitAll()")
public class EmptyCommand{
    @RequestMapping({"/"})
    @Secured("ROLE_ADMIN")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.MAIN_PAGE_PATH));
    }
}
