package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.resource.ConfigurationManager;

public class ToUserProfile implements ActionCommand {
    private static final String USER_PROFILE_PAGE_PATH = "path.page.userProfile";

    @Override
    public String execute(SessionRequestContent request) {
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
    }
}
