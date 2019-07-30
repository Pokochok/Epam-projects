package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.resource.ConfigurationManager;

public class BackToMainCommand implements ActionCommand {
    private static final String MAIN_PAGE_PATH = "path.page.main";

    @Override
    public String execute(SessionRequestContent request) {
        return ConfigurationManager.getProperty(MAIN_PAGE_PATH);
    }
}
