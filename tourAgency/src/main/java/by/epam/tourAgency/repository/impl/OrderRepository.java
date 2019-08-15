package by.epam.tourAgency.repository.impl;

import by.epam.tourAgency.connectionpool.ProxyConnectionPool;
import by.epam.tourAgency.entity.Order;
import by.epam.tourAgency.exception.ConnectionPoolException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.Repository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByIdSpecification;
import by.epam.tourAgency.specification.impl.client.FindClientByIdSpecification;
import by.epam.tourAgency.specification.impl.ticket.FindTicketByIdSpecification;
import by.epam.tourAgency.specification.impl.tour.FindTourByIdSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;

public class OrderRepository implements Repository<Order> {

    private OrderRepository() {
    }

    private static class RepositoryHolder {
        private static final OrderRepository REPOSITORY = new OrderRepository();
    }

    public static OrderRepository getInstance() {
        return RepositoryHolder.REPOSITORY;
    }

    @Override
    public void add(Order item, Specification specification) throws RepositoryException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("New order was added");
        } catch (SQLException e) {
            LOGGER.error("Error in adding tour: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool: ");
            throw new RepositoryException(e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(Order entity, Specification specification) throws RepositoryException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("Order was updated");
        } catch (SQLException e) {
            LOGGER.error("Error in updating order: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool: ");
            throw new RepositoryException(e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void remove(Order entity, Specification specification) throws RepositoryException {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing the order: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool: ");
            throw new RepositoryException(e);
        }
    }

    @Override
    public Set<Order> query(Specification specification) throws RepositoryException {
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
                Specification tourQuery = new FindTourByIdSpecification(resultSet.getInt("id_tour"));
                Specification ticketQuery = new FindTicketByIdSpecification(resultSet.getInt("id_ticket"));
                Specification userQuery = new FindClientByIdSpecification(resultSet.getInt("id_client"));
                Specification agentQuery = new FindAgentByIdSpecification(resultSet.getInt("id_agent"));
                Order order = new Order.OrderBuilder()
                .setId(resultSet.getInt("id"))
                .setPaymentState(Boolean.valueOf(resultSet.getString("payment_state")))
                .setTour(TourRepository.getInstance().query(tourQuery).iterator().next())
                .setTicket(TicketRepository.getInstance().query(ticketQuery).iterator().next())
                .setClient(UserRepository.getInstance().query(userQuery).iterator().next())
                .setAgent(UserRepository.getInstance().query(agentQuery).iterator().next()).build();
                orderSet.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("Error in query: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
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
        return orderSet;
    }
}
