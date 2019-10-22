package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.epam.touragency.util.PageMsgConstant.TO_LOGIN_PAGE_PATH;

@Controller
public class ToLoginCommand {
    @RequestMapping("/to_login")
    public String execute(){
        return ConfigurationManager.getProperty(TO_LOGIN_PAGE_PATH);
    }
}