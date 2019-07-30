package by.epam.dbworker.repository.impl;

import by.epam.dbworker.connectionpool.ProxyConnectionPool;
import by.epam.dbworker.entity.Order;
import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.specification.Specification;
import by.epam.dbworker.specification.impl.clientspecification.FindClientByIdSpecification;
import by.epam.dbworker.specification.impl.ticketspecification.FindTicketByIdSpecification;
import by.epam.dbworker.specification.impl.tourspecification.FindTourByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class BookingRepository implements Repository<Order> {
        private static final Logger LOGGER = LogManager.getLogger();

    private BookingRepository() {
        }

        private static class RepositoryHolder{
        private static final BookingRepository REPOSITORY = new BookingRepository();
    }

    public static BookingRepository getInstance(){
        return RepositoryHolder.REPOSITORY;
    }

    @Override
    public void add(Order item, Specification specification) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("New order was added");
        } catch (SQLException e) {
            LOGGER.error("Error in adding tour: ", e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(Order entity, Specification specification) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("Order was updated");
        } catch (SQLException e) {
            LOGGER.error("Error in updating order: ", e);
            // TODO: 24/06/2019 Exception
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void remove(Order entity, Specification specification) {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing the order: ", e);
        }
    }

    @Override
    public Set<Order> query(Specification specification) {
        Set<Order> orderSet = new HashSet<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setPaymentState(Boolean.valueOf(resultSet.getString("payment_state")));
                Specification tourQuery = new FindTourByIdSpecification(resultSet.getInt("id_tour"));
                Specification ticketQuery = new FindTicketByIdSpecification(resultSet.getInt("id_ticket"));
                Specification userQuery = new FindClientByIdSpecification(resultSet.getInt("id_client"));
                order.setTour(TourRepository.getInstance().query(tourQuery).iterator().next());
                order.setTicket(TicketRepository.getInstance().query(ticketQuery).iterator().next());
                order.setUser(UserRepository.getInstance().query(tourQuery).iterator().next());
                orderSet.add(order);
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
        return orderSet;
    }
}
