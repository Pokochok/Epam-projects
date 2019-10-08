package by.epam.touragency.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order.OrderBuilder().build();
        order.setId(resultSet.getInt("id"));
        order.setPaymentState(resultSet.getBoolean("payment_state"));
        order.setTourId(resultSet.getInt("id_tour"));
        order.setTicketId(resultSet.getInt("id_ticket"));
        order.setClientId(resultSet.getInt("id_client"));
        order.setAgentId(resultSet.getInt("id_agent"));
        return order;
    }
}
