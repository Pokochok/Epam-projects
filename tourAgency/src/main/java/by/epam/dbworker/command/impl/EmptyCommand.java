package by.epam.dbworker.command.impl;


import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {
    private static final String WELCOME_PAGE_PATH = "path.page.index";

    @Override
    public String execute(SessionRequestContent request) {
        String page = ConfigurationManager.getProperty(WELCOME_PAGE_PATH);
        return page;
    }
}
