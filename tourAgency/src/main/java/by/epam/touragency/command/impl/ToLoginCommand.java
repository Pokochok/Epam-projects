package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.resource.ConfigurationManager;

import static by.epam.touragency.util.PageMsgConstant.TO_LOGIN_PAGE_PATH;

public class ToLoginCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        return ConfigurationManager.getProperty(TO_LOGIN_PAGE_PATH);
    }
}
