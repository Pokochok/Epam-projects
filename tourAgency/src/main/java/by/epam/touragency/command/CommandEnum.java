package by.epam.touragency.command;

import by.epam.touragency.command.impl.*;

/**
 * Enum for defining command, received from request
 */
public enum CommandEnum {


;

    ActionCommand command;

    /**
     * Returns command, which was created
     * @return command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
