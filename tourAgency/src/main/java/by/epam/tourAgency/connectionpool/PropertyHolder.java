package by.epam.tourAgency.connectionpool;

import by.epam.tourAgency.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;

/**
 * Class, which contains all properties, to connect
 * with database, the connection pool needs
 */
public class PropertyHolder {
    private static final String FILE_NAME = "dbdriver/driverProp.properties";
    private static final String DRIVER_FIELD = "driverName";
    private static final String USER_FIELD = "dbUser";
    private static final String PASSWORD_FIELD = "dbPassword";
    private static final String URL_FIELD = "URL";
    private static final String POOL_SIZE = "poolSize";
    private static final String INIT_COUNT = "initCount";

    /**
     * Contains a driver name
     */
    private final String driverName;
    /**
     * Contains a user name
     */
    private final String userName;
    /**
     * Contains a password
     */
    private final String password;
    /**
     * Contains a url
     */
    private final String url;
    /**
     * Contains a size of connection pool
     */
    private final int poolSize;
    /**
     * Contains an amount of connections to initialize firs time
     */
    private final int initCount;
    /**
     * Contains instance of PropertyHolder
     */
    private static PropertyHolder instance;

    /**
     * Constructor for database with another url
     * @param url of database to connect
     */
    private PropertyHolder(String url){
        PropertyHolder propertyHolder = new PropertyHolder();
        driverName = propertyHolder.driverName;
        userName = propertyHolder.userName;
        password = propertyHolder.password;
        poolSize = propertyHolder.poolSize;
        initCount = propertyHolder.initCount;
        this.url = url;
    }

    /**
     * Constructor for main database
     */
    private PropertyHolder() {
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

    public static PropertyHolder getInstance(){
        if (instance == null){
            instance = new PropertyHolder();
        }
        return instance;
    }


    public static PropertyHolder getInstance(String url){
        if (instance == null){
            instance = new PropertyHolder(url);
        }
        return instance;
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
