package by.epam.touragency.specification.impl.user;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateUserLoginByIdSpecification implements Specification<User> {
    private static final String UPDATE_LOGIN_SPECIFICATION_SQL_BY_ID =
            "UPDATE users SET password=? WHERE id=?;";
    private String login;
    private int id;

    public UpdateUserLoginByIdSpecification(String login, int id) {
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