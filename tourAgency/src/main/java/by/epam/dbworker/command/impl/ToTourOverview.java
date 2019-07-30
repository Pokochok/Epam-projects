package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.resource.ConfigurationManager;

public class ToTourOverview implements ActionCommand {
    private static final String TOUR_OVERVIEW_PAGE_PATH = "path.page.tourOverview";

    @Override
    public String execute(SessionRequestContent request) {
        return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
    }
}
