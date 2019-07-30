package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.resource.ConfigurationManager;

public class ToTourSearch implements ActionCommand {
    private static final String TO_TOUR_SEARCH_PAGE_PATH = "path.page.tourSearch";

    @Override
    public String execute(SessionRequestContent request) {
        return ConfigurationManager.getProperty(TO_TOUR_SEARCH_PAGE_PATH);
    }
}
