package by.epam.dbworker.connectionpool;

import by.epam.dbworker.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProxyConnectionPool {
    private BlockingQueue<ProxyConnection> availableConnections;
    private List<ProxyConnection> usingConnections;
    private PropertyHolder propertyHolder;
    private static final Logger LOGGER = LogManager.getLogger();

    private ProxyConnectionPool() {
        propertyHolder = new PropertyHolder();
        try {
            Class.forName(propertyHolder.getDriverName());
            LOGGER.debug("Driver was register");
        } catch ( ClassNotFoundException e) {
            LOGGER.error("Error in register driver: ", e);
            throw new ConnectionPoolException("Error in register driver: ", e);
        }
        init();
    }

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
        // TODO есди коннекшенов меньше, попробовать досоздать до нужнлого кол-ва и записать в логи
    }

    private static class ConnectionPoolHolder {
        private static ProxyConnectionPool POOL = new ProxyConnectionPool();
    }

    public static ProxyConnectionPool getInstance() {
        return ConnectionPoolHolder.POOL;
    }

    private void createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(propertyHolder.getUrl(),
                    propertyHolder.getUserName(), propertyHolder.getPassword());
            availableConnections.offer(new ProxyConnection(connection));
            LOGGER.debug("New connection was offered to available");
        } catch (SQLException e) {
            LOGGER.error("Error in initializing connection: ", e);
        }
    }

    public Connection takeConnection() {// FIXME: 11/07/2019 Попробовать вынести добавление новых в отдельный поток
        ProxyConnection connection = null;
        if (availableConnections.size() == 0 && usingConnections.size() < propertyHolder.getPoolSize()) {
            LOGGER.debug("A lack of connections");
            createConnection();
        }
        try {
            connection = availableConnections.take();
            usingConnections.add(connection); //тут был available
        } catch (InterruptedException e) {
            LOGGER.error("Error in taking connection from pool: ", e);
            Thread.currentThread().interrupt();
        }
        LOGGER.debug("User get a connection");
        return connection;
    }
    // TODO таймер таск время от времени запускается и проверяет целостность коннекшенов

    public boolean returnConnection(Connection connection) {
        boolean flag = false;
        if (connection instanceof ProxyConnection) {
            usingConnections.remove(connection);
            flag = availableConnections.offer((ProxyConnection) connection);
            LOGGER.debug("User returned connection to available");
        }
        return flag;
    }

    private void deregisterDriver() {
        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        java.sql.Driver d = null;
        while(drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                LOGGER.debug(String.format("Driver %s deregistered", d));
            } catch (SQLException ex) {
                LOGGER.error(String.format("Error deregister driver %s", d), ex);
            }
        }
    }

    public void closePool() {
        for (int i = 0; i < availableConnections.size();) {
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
