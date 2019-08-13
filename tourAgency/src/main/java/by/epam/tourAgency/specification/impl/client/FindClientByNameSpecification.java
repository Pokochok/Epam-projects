package by.epam.tourAgency.specification.impl.client;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindClientByNameSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_NAME = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM clients WHERE name=?;";
    private String name;

    public FindClientByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_NAME;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(name);
        return values;
    }
}
