package by.epam.touragency.logic;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.command.CommandEnum;
import by.epam.touragency.command.impl.EmptyCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.resource.MessageManager;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;

/**
 * For defining command
 */
public class DefineActionCommandLogic {
    /**
     * Defines action command according to request parameters
     * @param request object of SessionRequestContent type, which
     *                provides with parameters
     * @return instance of command
     */
    public ActionCommand defineCommand(SessionRequestContent request) {
        ActionCommand current = null;
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
//            return new EmptyCommand();
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Wrong action detected");
            request.setAttribute("wrongAction", action + MessageManager.getProperty("logIn.message.wrongAction",
                    new Locale(request.getSessionAttribute("language").toString())));
//            current = new EmptyCommand();
        }
        return current;
    }
}