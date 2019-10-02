package by.epam.touragency.command.impl;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.MAIN_PAGE_PATH;

@Controller
public class BackToMainCommand {
    @RequestMapping("/back_to_main")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(MAIN_PAGE_PATH));
    }
}
