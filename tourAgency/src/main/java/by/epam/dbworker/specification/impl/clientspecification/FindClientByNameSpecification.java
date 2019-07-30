package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindClientByNameSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_NAME = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM clients WHERE name=?;";
    private String name;
    private ArrayDeque values;

    public FindClientByNameSpecification(String name) {
        this.name = name;
        values = new ArrayDeque(1);
        values.push(name);
    }

    @Override
    public boolean specify(User entity) {
        return name.equals(entity.getName());
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_NAME;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
