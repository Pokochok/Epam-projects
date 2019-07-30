package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.resource.ConfigurationManager;

public class ToAboutCompany implements ActionCommand {
    private static final String ABOUT_US_PAGE_PATH = "path.page.aboutUs";

    @Override
    public String execute(SessionRequestContent request) {
        return ConfigurationManager.getProperty(ABOUT_US_PAGE_PATH);
    }
}
