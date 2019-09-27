package by.epam.touragency.specification.impl.client;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindClientBySurnameSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_SURNAME = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM clients WHERE surname=?;";
    private String surname;

    public FindClientBySurnameSpecification(String surname) {
        this.surname = surname;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_SURNAME;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(surname);
        return values;
    }
}
