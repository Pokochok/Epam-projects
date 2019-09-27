package by.epam.touragency.specification.impl.client;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientEmailByIdSpecification implements Specification<User> {
    private static final String UPDATE_EMAIL_SPECIFICATION_SQL_BY_ID =
            "UPDATE clients SET email=? WHERE id=?;";
    private String email;
    private int id;

    public UpdateClientEmailByIdSpecification(String email, int id) {
        this.email = email;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_EMAIL_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(email);
        values.push(id);
        return values;
    }
}
