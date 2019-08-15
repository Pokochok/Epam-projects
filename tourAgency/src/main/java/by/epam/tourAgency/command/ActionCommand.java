package by.epam.tourAgency.command;

import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.exception.CommandException;

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
     * @see by.epam.tourAgency.exception.LogicException
     */
    String execute(SessionRequestContent content) throws CommandException;
}
