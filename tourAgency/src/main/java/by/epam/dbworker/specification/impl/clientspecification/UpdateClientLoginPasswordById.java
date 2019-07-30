package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientLoginPasswordById implements Specification<User> {
    private static final String UPDATE_LOGIN_PASSWORD_SPECIFICATION_SQL_BY_ID =
            "UPDATE clients SET login=?, password=? WHERE id=?;";
    private String login;
    private String password;

    private int id;
    private ArrayDeque values;

    public UpdateClientLoginPasswordById(String login, String password, int id) {
        this.login = login;
        this.password = password;
        this.id = id;
        values = new ArrayDeque(3);
        values.push(login);
        values.push(password);
        values.push(id);
    }

    @Override
    public boolean specify(User client) {
        return client.getId() == id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_LOGIN_PASSWORD_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
