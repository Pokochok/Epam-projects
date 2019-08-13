package by.epam.tourAgency.specification.impl.agent;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class AddAgentSpecification implements Specification<User> {
    private static final String ADD_AGENT_SQL_ROW = "INSERT INTO agents(name, surname, email, phone_number, login," +
            " password, role) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private User user;

    public AddAgentSpecification(User user) {
        this.user = user;
    }

    @Override
    public String sqlQuery() {
        return ADD_AGENT_SQL_ROW;
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
