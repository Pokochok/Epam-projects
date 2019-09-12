package by.epam.tourAgency.connectionpool;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.*;

public class ProxyConnectionPoolTest {
    @AfterTest
    public void after(){
        ProxyConnectionPool.getInstance().closePool();
    }

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