package by.epam.tourAgency.repository.impl;

import by.epam.tourAgency.connectionpool.ProxyConnectionPool;
import by.epam.tourAgency.entity.Ticket;
import by.epam.tourAgency.exception.ConnectionPoolException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.Repository;
import by.epam.tourAgency.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;

public class TicketRepository implements Repository<Ticket> {

    private TicketRepository() {
    }

    private static class RepositoryHolder {
        private static final TicketRepository REPOSITORY = new TicketRepository();
    }

    public static TicketRepository getInstance() {
        return RepositoryHolder.REPOSITORY;
    }

    @Override
    public void add(Ticket item, Specification specification) throws RepositoryException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("New ticket was added");
        } catch (SQLException e) {
            LOGGER.error("Error in adding ticket: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
            throw new RepositoryException(e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(Ticket entity, Specification specification) throws RepositoryException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("Ticket was updated");
        } catch (SQLException e) {
            LOGGER.error("Error in updating ticket: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
            throw new RepositoryException(e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void remove(Ticket entity, Specification specification) throws RepositoryException {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing the ticket: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
            throw new RepositoryException(e);
        }
    }

    @Override
    public Set<Ticket> query(Specification specification) throws RepositoryException {
        Set<Ticket> ticketSet = new HashSet<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket.TicketBuilder()
                .setId(resultSet.getInt("id"))
                .setFlightNumber(resultSet.getInt("flight_number"))
                .setTicketNumber(resultSet.getInt("ticket_number"))
                .setDepartureCity(resultSet.getString("departure_city"))
                .setArrivalCity(resultSet.getString("arrival_city"))
                .setDepartureDateTime(resultSet.getLong("departure_datetime"))
                .setArrivalDateTime(resultSet.getLong("arrival_datetime")).build();
                ticketSet.add(ticket);
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
        return ticketSet;
    }
}
