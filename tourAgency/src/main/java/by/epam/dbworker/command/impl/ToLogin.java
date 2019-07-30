package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.resource.ConfigurationManager;

public class ToLogin implements ActionCommand {
    private static final String TO_LOGIN_PAGE_PATH = "path.page.login";

    @Override
    public String execute(SessionRequestContent request) {
        return ConfigurationManager.getProperty(TO_LOGIN_PAGE_PATH);
    }
}
