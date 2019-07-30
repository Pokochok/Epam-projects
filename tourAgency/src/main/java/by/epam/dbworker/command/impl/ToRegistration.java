package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.resource.ConfigurationManager;

public class ToRegistration implements ActionCommand {
    private static final String TO_REGISTRATION_PAGE_PATH = "path.page.registration";

    @Override
    public String execute(SessionRequestContent request) {
        return ConfigurationManager.getProperty(TO_REGISTRATION_PAGE_PATH);
    }
}
