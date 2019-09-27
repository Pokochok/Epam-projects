package by.epam.touragency.exception;

/**
 * Can be thrown in commends layer
 * @see by.epam.touragency.command.ActionCommand
 */
public class CommandException extends Exception {
    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
