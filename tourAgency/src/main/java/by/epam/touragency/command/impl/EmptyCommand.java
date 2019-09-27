package by.epam.touragency.command.impl;


import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.resource.ConfigurationManager;

import static by.epam.touragency.util.PageMsgConstant.WELCOME_PAGE_PATH;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = ConfigurationManager.getProperty(WELCOME_PAGE_PATH);
        return page;
    }
}
