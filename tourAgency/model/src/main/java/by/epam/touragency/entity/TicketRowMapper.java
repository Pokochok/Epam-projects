package by.epam.touragency.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Ticket.TicketBuilder()
                .setId(resultSet.getInt("id"))
                .setFlightNumber(resultSet.getInt("flight_number"))
                .setTicketNumber(resultSet.getInt("ticket_number"))
                .setDepartureCity(resultSet.getString("departure_city"))
                .setArrivalCity(resultSet.getString("arrival_city"))
                .setDepartureDateTime(resultSet.getLong("departure_datetime"))
                .setArrivalDateTime(resultSet.getLong("arrival_datetime")).build();
    }
}
