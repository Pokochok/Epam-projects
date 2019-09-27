package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.resource.ConfigurationManager;

import static by.epam.touragency.util.PageMsgConstant.MAIN_PAGE_PATH;

public class BackToMainCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        return ConfigurationManager.getProperty(MAIN_PAGE_PATH);
    }
}
