package by.epam.touragency.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourRowMapper implements RowMapper<Tour> {
    @Override
    public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Tour.TourBuilder()
                .setId(resultSet.getInt("id"))
                .setTourName(resultSet.getString("tour_name"))
                .setDepartureDate(resultSet.getLong("departure_date"))
                .setArrivalDate(resultSet.getLong("arrival_date"))
                .setDepartureCity(resultSet.getString("departure_city"))
                .setArrivalCity(resultSet.getString("arrival_city"))
                .setArrivalCountry(resultSet.getString("arrival_country"))
                .setHotel(resultSet.getString("hotel"))
                .setNutrition(resultSet.getString("nutrition"))
                .setAdultsNumber(resultSet.getInt("adults_number"))
                .setChildrenNumber(resultSet.getInt("children_number"))
                .setPrice(resultSet.getBigDecimal("price"))
                .setStatus(resultSet.getString("status")).build();
    }
}
