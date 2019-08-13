package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.resource.ConfigurationManager;

import static by.epam.tourAgency.util.PageMsgConstant.TO_TICKET_REGISTRATION_PAGE_PATH;

public class ToTicketRegistrationCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        return ConfigurationManager.getProperty(TO_TICKET_REGISTRATION_PAGE_PATH);
    }
}
