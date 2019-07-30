package by.epam.dbworker.repository.impl;

import by.epam.dbworker.connectionpool.ProxyConnectionPool;
import by.epam.dbworker.entity.Ticket;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TicketRepository implements Repository<Ticket> {
    private static final Logger LOGGER = LogManager.getLogger();

    private TicketRepository() {
    }

    private static class RepositoryHolder{
        private static final TicketRepository REPOSITORY = new TicketRepository();
    }

    public static TicketRepository getInstance(){
        return RepositoryHolder.REPOSITORY;
    }

    @Override
    public void add(Ticket item, Specification specification) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("New ticket was added");
        } catch (SQLException e) {
            LOGGER.error("Error in adding ticket: ", e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(Ticket entity, Specification specification) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("Ticket was updated");
        } catch (SQLException e) {
            LOGGER.error("Error in updating ticket: ", e);
            // TODO: 24/06/2019 Exception
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void remove(Ticket entity, Specification specification) {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing the ticket: ", e);
        }
    }

    @Override
    public Set<Ticket> query(Specification specification) {
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
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setFlightNumber(resultSet.getInt("flight_number"));
                ticket.setTicketNumber(resultSet.getInt("ticket_number"));
                ticket.setDepartureAirport(resultSet.getString("departure_airport"));
                ticket.setArrivalAirport(resultSet.getString("arrival_airport"));
                ticket.setDepartureDateTime(resultSet.getLong("departure_datetime"));
                ticket.setArrivalDateTime(resultSet.getLong("arrival_datetime"));
                ticketSet.add(ticket);
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
        return ticketSet;
    }
}
