package by.epam.dbworker.repository;

import by.epam.dbworker.connectionpool.ProxyConnectionPool;
import by.epam.dbworker.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Set;

public interface Repository<T> {
    void add(T item, Specification specification);
    void update(T entity, Specification specification); // FIXME: 16/07/2019 убрать T entity из параметров, сделать абстрактным классом и имплементировать репозиторий с добавлением default  методов
    void remove(T entity, Specification specification);
    Set<T> query (Specification specification);

    default boolean isExistsQuery(Specification specification) {
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
    //        LOGGER.error("Error in query: ", e);
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
        return flag;
    }

    default void setPreparedStatementValues(PreparedStatement preparedStatement, Specification specification) throws SQLException {
        int i = 0;
        ArrayDeque params = specification.getParameterQueue();
        while (!params.isEmpty()) {
            i++;
            if (params.getLast() instanceof Integer){
                preparedStatement.setInt(i, (Integer) params.removeLast());
            }else if (params.getLast() instanceof Long){
                preparedStatement.setLong(i, (Long) params.removeLast());
            }else if (params.getLast() instanceof BigDecimal){
                preparedStatement.setBigDecimal(i, (BigDecimal) params.removeLast());
            }else {
                preparedStatement.setString(i, String.valueOf(params.removeLast()));
            }
        }
    }

    default void closePreparedStatement (PreparedStatement preparedStatement) {
        Logger logger = LogManager.getLogger();
        try {
            preparedStatement.close();
            logger.debug("PreparedStatement was closed");
        } catch (SQLException e) {
            logger.error("Error when closing PreparedStatement: " + e);
        }
    }
    default void closeResultSet (ResultSet resultSet) {
        Logger logger = LogManager.getLogger();
        try {
            resultSet.close();
            logger.debug("ResultSet was closed");
        } catch (SQLException e) {
            logger.error("Error when closing ResultSet: " + e);
        }
    }
}
