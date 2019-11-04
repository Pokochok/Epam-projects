package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.PageMsgConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToLoginCommand {
    @RequestMapping("/to_login")
    public String execute(){
        return ConfigurationManager.getProperty(PageMsgConstant.TO_LOGIN_PAGE_PATH);
    }
}