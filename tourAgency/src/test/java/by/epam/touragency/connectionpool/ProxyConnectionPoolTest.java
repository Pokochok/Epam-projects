package by.epam.touragency.connectionpool;


import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ProxyConnectionPoolTest {

    @Test
    public void testGetInstance() {
        ProxyConnectionPool connectionPool = ProxyConnectionPool.getInstance();
        boolean actual = connectionPool != null;
        Assert.assertTrue(actual);
    }

    @Test
    public void testTakeConnection() {
        Connection connection = ProxyConnectionPool.getInstance().takeConnection();
        boolean actual = connection instanceof ProxyConnection;
        Assert.assertTrue(actual);
    }

    @Test
    public void testReturnConnectionTrue() {
        Connection connection = ProxyConnectionPool.getInstance().takeConnection();
        boolean actual = ProxyConnectionPool.getInstance().returnConnection(connection);
        Assert.assertTrue(actual);
    }

    @Test
    public void testReturnConnectionFalse() {
        Connection connection = null;
        boolean actual = ProxyConnectionPool.getInstance().returnConnection(connection);
        Assert.assertFalse(actual);
    }
}