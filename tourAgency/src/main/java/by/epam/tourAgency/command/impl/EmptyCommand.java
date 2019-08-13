package by.epam.tourAgency.command.impl;


import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.resource.ConfigurationManager;

import static by.epam.tourAgency.util.PageMsgConstant.WELCOME_PAGE_PATH;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = ConfigurationManager.getProperty(WELCOME_PAGE_PATH);
        return page;
    }
}
