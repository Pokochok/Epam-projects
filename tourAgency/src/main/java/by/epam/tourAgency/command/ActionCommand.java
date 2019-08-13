package by.epam.tourAgency.command;

import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.exception.CommandException;

public interface ActionCommand {
    String execute(SessionRequestContent content) throws CommandException;
}
