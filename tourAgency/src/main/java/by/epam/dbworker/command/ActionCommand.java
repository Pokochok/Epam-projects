package by.epam.dbworker.command;

import by.epam.dbworker.controller.SessionRequestContent;

public interface ActionCommand {
    String execute(SessionRequestContent request);
}
