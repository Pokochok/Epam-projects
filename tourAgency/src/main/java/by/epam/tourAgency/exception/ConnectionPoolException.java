package by.epam.tourAgency.exception;

/**
 * Can be thrown in connection pool layer
 * @see by.epam.tourAgency.connectionpool.ProxyConnectionPool
 */
public class ConnectionPoolException extends RuntimeException{
    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
