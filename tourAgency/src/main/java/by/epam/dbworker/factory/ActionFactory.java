package by.epam.dbworker.factory;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.command.CommandEnum;
import by.epam.dbworker.command.impl.EmptyCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.resource.MessageManager;

import java.util.Locale;

public class ActionFactory {
    public ActionCommand defineCommand(SessionRequestContent request) {
        ActionCommand current = null;
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("logIn.message.wrongAction",
                    new Locale(request.getSessionAttribute("language").toString())));
            current = new EmptyCommand();
        }
        return current;
    }
}