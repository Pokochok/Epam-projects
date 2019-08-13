package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.resource.ConfigurationManager;

import static by.epam.tourAgency.util.PageMsgConstant.USER_PROFILE_PAGE_PATH;

public class ToUserProfileCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
    }
}
