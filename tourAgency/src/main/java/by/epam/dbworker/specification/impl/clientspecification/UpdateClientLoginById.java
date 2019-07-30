package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientLoginById implements Specification<User> {
    private static final String UPDATE_LOGIN_SPECIFICATION_SQL_BY_ID =
            "UPDATE clients SET password=? WHERE id=?;";
    private String login;
private ArrayDeque values;
    private int id;

    public UpdateClientLoginById(String login, int id) {
        this.login = login;
        this.id = id;
        values=new ArrayDeque(1);
        values.push(login);
        values.push(id);
    }

    @Override
    public boolean specify(User entity) {
        return id == entity.getId();
    }

    @Override
    public String sqlQuery() {
        return UPDATE_LOGIN_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}