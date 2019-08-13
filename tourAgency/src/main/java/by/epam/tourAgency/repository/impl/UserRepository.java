package by.epam.tourAgency.repository.impl;

import by.epam.tourAgency.connectionpool.ProxyConnectionPool;
import by.epam.tourAgency.entity.Role;
import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.exception.ConnectionPoolException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.Repository;
import by.epam.tourAgency.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserRepository implements Repository<User> {
    private static final Logger LOGGER = LogManager.getLogger();

    private UserRepository() {
    }

    private static class RepositoryHolder {
        private static final UserRepository USER_REPOSITORY = new UserRepository();
    }

    public static UserRepository getInstance() {
        return RepositoryHolder.USER_REPOSITORY;
    }

    @Override
    public void add(User user, Specification specification) throws RepositoryException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("New " + user.getRole() + " was added");
        } catch (SQLException e) {
            LOGGER.error("Error in adding user: ", e);
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool", e);
            throw new RepositoryException(e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(User user, Specification specification) throws RepositoryException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("User was updated");
        } catch (SQLException e) {
            LOGGER.error("Error in updating: ", e);
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool", e);
            throw new RepositoryException(e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void remove(User user, Specification specification) throws RepositoryException {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing: ", e);
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool", e);
            throw new RepositoryException(e);
        }
    }

    @Override
    public Set<User> query(Specification specification) throws RepositoryException {
        Set<User> userSet = new HashSet<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User.UserBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setEmail(resultSet.getString("email"))
                .setPhoneNumber(resultSet.getString("phone_number"))
                .setLogin(resultSet.getString("login"))
                .setPassword(resultSet.getString("password"))
                .setRole(Role.valueOf(resultSet.getString("role")))
                .setStatus(resultSet.getString("status")).build();
                userSet.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Error in query: ", e);
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool", e);
            throw new RepositoryException(e);
        } finally {
            if (resultSet != null) {
                closeResultSet(resultSet);
            }
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
        return userSet;
    }
}
