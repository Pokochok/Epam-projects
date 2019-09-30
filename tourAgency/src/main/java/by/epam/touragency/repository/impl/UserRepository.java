package by.epam.touragency.repository.impl;

import by.epam.touragency.connectionpool.ProxyConnectionPool;
import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.User;
import by.epam.touragency.exception.ConnectionPoolException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;

@Component
public class UserRepository implements Repository<User> {

    private JdbcTemplate jdbcTemplate;
    private static UserRepository userRepository;

    private UserRepository() {
    }

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userRepository = this;
    }

    public static UserRepository getInstance() {
        if (userRepository != null) {
            return userRepository;
        } else {
            userRepository = new UserRepository();
            return userRepository;
        }
    }

    @Override
    public void add(User user, Specification specification) throws RepositoryException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = ProxyConnectionPool.getInstance().takeConnection();
//            preparedStatement = connection.prepareStatement(specification.sqlQuery());
//            setPreparedStatementValues(preparedStatement, specification);
//            preparedStatement.executeUpdate();
//            LOGGER.info("New " + user.getRole() + " was added");
//        } catch (SQLException e) {
//            LOGGER.error("Error in adding user: ");
//            throw new RepositoryException(e);
//        } catch (ConnectionPoolException e) {
//            LOGGER.fatal("Error in connection pool");
//            throw new RepositoryException(e);
//        } finally {
//            if (preparedStatement != null) {
//                closePreparedStatement(preparedStatement);
//            }
//            ProxyConnectionPool.getInstance().returnConnection(connection);
//        }
        jdbcTemplate.update(specification.sqlQuery(), user.getName(), user.getSurname(), user.getEmail(),
                user.getPhoneNumber(), user.getLogin(), user.getPassword(), user.getRole().toString());
    }

    @Override
    public void update(User user, Specification specification) throws RepositoryException {
//        PreparedStatement preparedStatement = null;
//        Connection connection = null;
//        try {
//            connection = ProxyConnectionPool.getInstance().takeConnection();
//            preparedStatement = connection.prepareStatement(specification.sqlQuery());
//            setPreparedStatementValues(preparedStatement, specification);
//            preparedStatement.executeUpdate();
//            LOGGER.info("User was updated");
//        } catch (SQLException e) {
//            LOGGER.error("Error in updating: ");
//            throw new RepositoryException(e);
//        } catch (ConnectionPoolException e) {
//            LOGGER.fatal("Error in connection pool");
//            throw new RepositoryException(e);
//        } finally {
//            if (preparedStatement != null) {
//                closePreparedStatement(preparedStatement);
//            }
//            ProxyConnectionPool.getInstance().returnConnection(connection);
//        }

        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public void remove(User user, Specification specification) throws RepositoryException {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
            throw new RepositoryException(e);
        }
    }

    @Override
    public Set<User> query(Specification specification) throws RepositoryException {
//        Set<User> userSet = new HashSet<>();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Connection connection = null;
//        try {
//            connection = ProxyConnectionPool.getInstance().takeConnection();
//            preparedStatement = connection.prepareStatement(specification.sqlQuery());
//            setPreparedStatementValues(preparedStatement, specification);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                User user = new User.UserBuilder()
//                        .setId(resultSet.getInt("id"))
//                        .setName(resultSet.getString("name"))
//                        .setSurname(resultSet.getString("surname"))
//                        .setEmail(resultSet.getString("email"))
//                        .setPhoneNumber(resultSet.getString("phone_number"))
//                        .setLogin(resultSet.getString("login"))
//                        .setPassword(resultSet.getString("password"))
//                        .setRole(Role.valueOf(resultSet.getString("role")))
//                        .setStatus(resultSet.getString("status")).build();
//                userSet.add(user);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("Error in query: ");
//            throw new RepositoryException(e);
//        } catch (ConnectionPoolException e) {
//            LOGGER.fatal("Error in connection pool");
//            throw new RepositoryException(e);
//        } finally {
//            if (resultSet != null) {
//                closeResultSet(resultSet);
//            }
//            if (preparedStatement != null) {
//                closePreparedStatement(preparedStatement);
//            }
//            ProxyConnectionPool.getInstance().returnConnection(connection);
//        }
//        return userSet;

        return new HashSet<>(jdbcTemplate.query(specification.sqlQuery(), specification.getParameterQueue().toArray(),
                (resultSet, i) -> new User.UserBuilder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setEmail(resultSet.getString("email"))
                        .setPhoneNumber(resultSet.getString("phone_number"))
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .setRole(Role.valueOf(resultSet.getString("role")))
                        .setStatus(resultSet.getString("status")).build()));

    }

    @Override
    public boolean isExistsQuery(Specification specification) throws RepositoryException {
        return !jdbcTemplate.query(specification.sqlQuery(), specification.getParameterQueue().toArray(),
                (resultSet, i) -> new User.UserBuilder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setEmail(resultSet.getString("email"))
                        .setPhoneNumber(resultSet.getString("phone_number"))
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .setRole(Role.valueOf(resultSet.getString("role")))
                        .setStatus(resultSet.getString("status")).build()).isEmpty();
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
