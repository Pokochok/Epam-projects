package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.resource.ConfigurationManager;

import static by.epam.tourAgency.util.PageMsgConstant.TO_LOGIN_PAGE_PATH;

public class ToLoginCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        return ConfigurationManager.getProperty(TO_LOGIN_PAGE_PATH);
    }
}
