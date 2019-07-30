package by.epam.dbworker.specification.impl.agentspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class AddAgentSpecification implements Specification<User> {
    private static final String ADD_AGENT_SQL_ROW = "INSERT INTO agents(name, surname, email, phone_number, login," +
            " password, role) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private ArrayDeque values;

    public AddAgentSpecification(User user) {
        values = new ArrayDeque(7);
        values.push(user.getName());
        values.push(user.getSurname());
        values.push(user.getEmail());
        values.push(user.getPhoneNumber());
        values.push(user.getLogin());
        values.push(user.getPassword());
        values.push(user.getRole().toString());
    }

    @Override
    public boolean specify(User entity) {
        return false;
    }

    @Override
    public String sqlQuery() {
        return ADD_AGENT_SQL_ROW;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
