package by.epam.dbworker.repository.impl;

import by.epam.dbworker.connectionpool.ProxyConnectionPool;
import by.epam.dbworker.entity.Role;
import by.epam.dbworker.entity.User;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
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
    public void add(User user, Specification specification) {
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
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(User user, Specification specification) {
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
            // TODO: 24/06/2019 Exception
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void remove(User user, Specification specification) {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing: ", e);
        }
    }

    @Override
    public Set<User> query(Specification specification) {
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
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setStatus(resultSet.getString("status"));
                userSet.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Error in query: ", e);
            // TODO: 24/06/2019 Exception
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
