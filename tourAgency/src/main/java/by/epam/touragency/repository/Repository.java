package by.epam.touragency.repository;

import by.epam.touragency.exception.ConnectionPoolException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.specification.Specification;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;

/**
 * Class, which simplifies database interaction
 * @param <T> the type of elements processed by this repository
 */
public interface Repository<T> {

    /**
     * Adds element in database according to specification
     * @param item
     * @param specification which determine adding
     * @throws RepositoryException if handled ConnectionPoolException or other
     */
    void add(T item, Specification specification) throws RepositoryException;

    /**
     * Updates element in database according to specification
     * @param entity
     * @param specification which determine updating
     * @throws RepositoryException if handled ConnectionPoolException or other
     */
    void update(T entity, Specification specification) throws RepositoryException;

    /**
     * Removes element in database according to specification
     * @param entity
     * @param specification which determine removing
     * @throws RepositoryException if handled ConnectionPoolException or other
     */
    void remove(T entity, Specification specification) throws RepositoryException;

    /**
     * Makes a request to database according to specification
     * @param specification which determine request
     * @return entities set
     * @throws RepositoryException  if handled ConnectionPoolException or other
     */
    Set<T> query(Specification specification) throws RepositoryException;

    /**
     * Defines if database has elements according to specification
     * @param specification which determine request
     * @return true if database found element, false - if not
     * @throws RepositoryException if handled ConnectionPoolException or other
     */
    boolean isExistsQuery(Specification specification) throws RepositoryException;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Connection connection = null;
//        boolean flag = false;
//        try {
////            connection = ProxyConnectionPool.getInstance().takeConnection();
//            preparedStatement = connection.prepareStatement(specification.sqlQuery());
//            setPreparedStatementValues(preparedStatement, specification);
//            resultSet = preparedStatement.executeQuery();
//            flag = resultSet.next();
//        } catch (SQLException e) {
//            LOGGER.error("Error thrown by prepared statement: ", e);
//            throw new RepositoryException(e);
//        } catch (ConnectionPoolException e) {
//            LOGGER.fatal("Error in connection pool: ", e);
//            throw new RepositoryException(e);
//        } finally {
//            if (resultSet != null) {
//                closeResultSet(resultSet);
//            }
//            if (preparedStatement != null) {
//                closePreparedStatement(preparedStatement);
//            }
////            ProxyConnectionPool.getInstance().returnConnection(connection);
//        }
//        return flag;
//    }

    /**
     * Sets values from specification to prepared statement
     * @param preparedStatement in which values will be sets
     * @param specification this values will be sets in prepared statement
     * @throws SQLException if prepared statement actions not completed with success
     */
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

    /**
     * Closes prepared statement
     * @param preparedStatement which will be closed
     */
    default void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
            LOGGER.debug("PreparedStatement was closed");
        } catch (SQLException e) {
            LOGGER.error("Error when closing PreparedStatement: ", e);
        }
    }

    /**
     * Closes result set
     * @param resultSet which will be closed
     */
    default void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
            LOGGER.debug("ResultSet was closed");
        } catch (SQLException e) {
            LOGGER.error("Error when closing ResultSet: ", e);
        }
    }
}
