package by.epam.touragency.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User.UserBuilder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setEmail(resultSet.getString("email"))
                .setPhoneNumber(resultSet.getString("phone_number"))
                .setLogin(resultSet.getString("login"))
                .setPassword(resultSet.getString("password"))
                .setRole(Role.valueOf(resultSet.getString("role")))
                .setStatus(resultSet.getString("status")).build();
    }
}
