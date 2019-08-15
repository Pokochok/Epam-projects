package by.epam.tourAgency.connectionpool;

import by.epam.tourAgency.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;

/**
 * Connection pool, which contains connections of ProxyConnection type.
 * Implemented as singletone.
 * @see ProxyConnection
 */
public class ProxyConnectionPool {
    /**
     * Contains connections, which are available for use
     */
    private BlockingQueue<ProxyConnection> availableConnections;
    /**
     * Contains connections, which are used and aren't available to take
     */
    private List<ProxyConnection> usingConnections;
    /**
     * PropertyHolder instance. Used to get properties to connect
     * with database
     * @see PropertyHolder
     */
    private PropertyHolder propertyHolder;

    /**
     * Construct a connection pool. Registers database driver.
     * Initialize a pool.
     */
    private ProxyConnectionPool() {
        propertyHolder = new PropertyHolder();
        registerDriver();
        LOGGER.debug("Driver was register");
        init();
    }

    /**
     * Registers database driver
     * Can throw ConnectionPoolException
     * @see ConnectionPoolException
     */
    private void registerDriver() {
        try {
            Class.forName(propertyHolder.getDriverName());
        } catch (ClassNotFoundException e) {
            LOGGER.error("Error in register driver: ");
            throw new ConnectionPoolException("Error in register driver: ", e);
        }
    }

    /**
     * Initialize a connection pool
     * Can throw ConnectionPoolException
     * @see ConnectionPoolException
     */
    private void init() {
        availableConnections = new LinkedBlockingQueue<>(propertyHolder.getPoolSize());
        usingConnections = new LinkedList<>();
        for (int i = 0; i < propertyHolder.getInitCount(); i++) {
            createConnection();
        }
        LOGGER.debug("ProxyConnectionPool was created");
        if (availableConnections.size() == 0) {
            LOGGER.fatal("Fatal error in initializer: connection pool hasn't any connections");
            throw new ConnectionPoolException("Connection pool hasn't any connections");
        }
    }

    /**
     * Inner instance-holder class
     */
    private static class ConnectionPoolHolder {
        /**
         * Hold the instance of connection pool
         */
        private static ProxyConnectionPool POOL = new ProxyConnectionPool();
    }

    /**
     * Get-method to get pool instance
     * @return connection pool instance
     */
    public static ProxyConnectionPool getInstance() {
        return ConnectionPoolHolder.POOL;
    }

    /**
     * Creates connection and put it into available
     * connections container
     * Can throw ConnectionPoolException
     * @see ConnectionPoolException
     */
    private void createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(propertyHolder.getUrl(),
                    propertyHolder.getUserName(), propertyHolder.getPassword());
            availableConnections.offer(new ProxyConnection(connection));
            LOGGER.debug("New connection was offered to available");
        } catch (SQLException e) {
            LOGGER.error("Error in initializing connection: ", e);
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Get-method to get connection. Takes available connection
     * from container, if it has available connection, put this connection
     * to using connection container. If available
     * connections container hasn't any connection, tries to create new
     * connection. Then returns this connection.
     * @return a connection
     */
    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            if (availableConnections.size() == 0 && usingConnections.size() < propertyHolder.getPoolSize()) {
                LOGGER.debug("A lack of connections");
                new Thread(this::createConnection).start();
            }
            connection = availableConnections.take();
            usingConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Error in taking connection from pool: ", e);
            Thread.currentThread().interrupt();
        }
        LOGGER.debug("User get a connection");
        return connection;
    }

    /**
     * Receive a connection, check it and put into available
     * connections container
     * @param connection received connection
     * @return result of returning. True if completes successfully, false - if not
     */
    public boolean returnConnection(Connection connection) {
        boolean flag = false;
        if (connection instanceof ProxyConnection) {
            usingConnections.remove(connection);
            flag = availableConnections.offer((ProxyConnection) connection);
            LOGGER.debug("User returned connection to available");
        }
        return flag;
    }

    /**
     * Deregisters database drivers
     */
    private void deregisterDriver() {
        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        java.sql.Driver d = null;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                LOGGER.debug(String.format("Driver %s deregistered", d));
            } catch (SQLException ex) {
                LOGGER.error(String.format("Error deregister driver %s", d), ex);
            }
        }
    }

    /**
     * Closes connections and deregisters drivers
     */
    public void closePool() {
        for (int i = 0; i < availableConnections.size(); ) {
            try {
                availableConnections.take().finallyClose();
                LOGGER.debug("Connection was closed");
            } catch (InterruptedException | SQLException e) {
                LOGGER.error("Error in closing connection: ", e);
            }
        }
        deregisterDriver();
    }
}
