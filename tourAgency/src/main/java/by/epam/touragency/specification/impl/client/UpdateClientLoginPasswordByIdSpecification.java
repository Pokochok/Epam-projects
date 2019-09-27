package by.epam.touragency.specification.impl.client;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientLoginPasswordByIdSpecification implements Specification<User> {
    private static final String UPDATE_LOGIN_PASSWORD_SPECIFICATION_SQL_BY_ID =
            "UPDATE clients SET login=?, password=? WHERE id=?;";
    private String login;
    private String password;
    private int id;

    public UpdateClientLoginPasswordByIdSpecification(String login, String password, int id) {
        this.login = login;
        this.password = password;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_LOGIN_PASSWORD_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(3);
        values.push(login);
        values.push(password);
        values.push(id);
        return values;
    }
}
