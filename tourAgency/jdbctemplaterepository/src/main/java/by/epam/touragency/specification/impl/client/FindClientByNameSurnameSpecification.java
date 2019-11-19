package by.epam.touragency.specification.impl.client;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindClientByNameSurnameSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_NAME_SURNAME = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM users WHERE name=? AND surname=? AND role='CLIENT';";
    private String name;
    private String surname;

    public FindClientByNameSurnameSpecification(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_NAME_SURNAME;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(name);
        values.push(surname);
        return values;
    }
}

