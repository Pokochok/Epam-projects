package by.epam.tourAgency.specification.impl.client;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindClientByIdSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM clients WHERE id=?;";
    private int id;

    public FindClientByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(id);
        return values;
    }
}
