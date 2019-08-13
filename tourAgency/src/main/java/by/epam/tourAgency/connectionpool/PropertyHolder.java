package by.epam.tourAgency.connectionpool;

import by.epam.tourAgency.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertyHolder {
    private static final String FILE_NAME = "dbdriver/driverProp.properties";
    private static final String DRIVER_FIELD = "driverName";
    private static final String USER_FIELD = "dbUser";
    private static final String PASSWORD_FIELD = "dbPassword";
    private static final String URL_FIELD = "URL";
    private static final String POOL_SIZE = "poolSize";
    private static final String INIT_COUNT = "initCount";

    private final String driverName;
    private final String userName;
    private final String password;
    private final String url;
    private final int poolSize;
    private final int initCount;

    private static final Logger LOGGER = LogManager.getLogger();

    PropertyHolder() {
        Properties properties = new Properties();
        try (InputStream inputStream = PropertyHolder.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            properties.load(inputStream);
            LOGGER.info("Properties was loaded");
            driverName = properties.getProperty(DRIVER_FIELD);
            userName = properties.getProperty(USER_FIELD);
            password = properties.getProperty(PASSWORD_FIELD);
            url = properties.getProperty(URL_FIELD);
            poolSize = Integer.parseInt(properties.getProperty(POOL_SIZE));
            initCount = Integer.parseInt(properties.getProperty(INIT_COUNT));
        } catch (IOException e) {
            LOGGER.fatal("Fatal error in reading file:", e);
            throw new ConnectionPoolException("Error in reading file: ", e);
        }
    }

    String getDriverName() {
        return driverName;
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    String getUrl() {
        return url;
    }

    int getInitCount() {
        return initCount;
    }

    int getPoolSize() {
        return poolSize;
    }
}
