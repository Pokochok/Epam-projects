package by.epam.tourAgency.specification.impl.client;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientLoginByIdSpecification implements Specification<User> {
    private static final String UPDATE_LOGIN_SPECIFICATION_SQL_BY_ID =
            "UPDATE clients SET password=? WHERE id=?;";
    private String login;
    private int id;

    public UpdateClientLoginByIdSpecification(String login, int id) {
        this.login = login;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_LOGIN_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values=new ArrayDeque<>(1);
        values.push(login);
        values.push(id);
        return values;
    }
}