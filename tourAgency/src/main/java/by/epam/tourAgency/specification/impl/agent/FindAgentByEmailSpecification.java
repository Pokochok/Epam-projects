package by.epam.tourAgency.specification.impl.agent;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindAgentByEmailSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_EMAIL = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM agents WHERE email=?;";
    private String email;

    public FindAgentByEmailSpecification(String email) {
        this.email = email;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_EMAIL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(email);
        return values;
    }
}
