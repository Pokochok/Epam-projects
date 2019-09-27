package by.epam.touragency.command;

import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.exception.CommandException;

/**
 * An command, which processing action, according to received parameters
 */
public interface ActionCommand {
    /**
     * Executes actions according to command purpose
     * @param content object of SessionRequestContent type, which contains values for processing action
     * @return page on which forward will be called
     * @throws CommandException if LogicException or some other was handle in the method
     * @see SessionRequestContent
     * @see by.epam.touragency.exception.LogicException
     */
    String execute(SessionRequestContent content) throws CommandException;
}
