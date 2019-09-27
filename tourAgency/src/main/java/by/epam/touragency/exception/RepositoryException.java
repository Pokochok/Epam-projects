package by.epam.touragency.exception;

/**
 * Can be thrown in connection pool layer
 * @see by.epam.touragency.repository.Repository
 */
public class RepositoryException extends Exception {
    public RepositoryException() {
        super();
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }
}
