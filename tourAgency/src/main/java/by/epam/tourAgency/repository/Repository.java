package by.epam.tourAgency.repository;

import by.epam.tourAgency.connectionpool.ProxyConnectionPool;
import by.epam.tourAgency.exception.ConnectionPoolException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.specification.Specification;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Set;

import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;

public interface Repository<T> {
    void add(T item, Specification specification) throws RepositoryException;

    void update(T entity, Specification specification) throws RepositoryException; // FIXME: 16/07/2019 убрать T entity из параметров, сделать абстрактным классом и имплементировать репозиторий с добавлением default  методов

    void remove(T entity, Specification specification) throws RepositoryException;

    Set<T> query(Specification specification) throws RepositoryException;

    default boolean isExistsQuery(Specification specification) throws RepositoryException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean flag = false;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            resultSet = preparedStatement.executeQuery();
            flag = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Error thrown by prepared statement: ", e);
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool: ", e);
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
        return flag;
    }

    default void setPreparedStatementValues(PreparedStatement preparedStatement, Specification specification) throws SQLException {
        int i = 0;
        ArrayDeque params = specification.getParameterQueue();
        while (!params.isEmpty()) {
            i++;
            if (params.getLast() instanceof Integer) {
                preparedStatement.setInt(i, (Integer) params.removeLast());
            } else if (params.getLast() instanceof Long) {
                preparedStatement.setLong(i, (Long) params.removeLast());
            } else if (params.getLast() instanceof BigDecimal) {
                preparedStatement.setBigDecimal(i, (BigDecimal) params.removeLast());
            } else {
                preparedStatement.setString(i, String.valueOf(params.removeLast()));
            }
        }
    }

    default void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
            LOGGER.debug("PreparedStatement was closed");
        } catch (SQLException e) {
            LOGGER.error("Error when closing PreparedStatement: ", e);
        }
    }

    default void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
            LOGGER.debug("ResultSet was closed");
        } catch (SQLException e) {
            LOGGER.error("Error when closing ResultSet: ", e);
        }
    }
}
