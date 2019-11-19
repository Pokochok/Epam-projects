package by.epam.touragency.specification.impl.user;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class AddUserSpecification implements Specification<User> {
    private static final String ADD_CLIENT_SQL_ROW = "INSERT INTO users(name, surname, email, phone_number, login," +
            " password, role) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private User user;

    public AddUserSpecification(User user) {
        this.user = user;
    }

    @Override
    public String sqlQuery() {
        return ADD_CLIENT_SQL_ROW;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(7);
        values.push(user.getName());
        values.push(user.getSurname());
        values.push(user.getEmail());
        values.push(user.getPhoneNumber());
        values.push(user.getLogin());
        values.push(user.getPassword());
        values.push(user.getRole().toString());
        return values;
    }
}
