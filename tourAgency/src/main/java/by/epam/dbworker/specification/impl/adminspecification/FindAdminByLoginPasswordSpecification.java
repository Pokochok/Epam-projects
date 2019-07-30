package by.epam.dbworker.specification.impl.adminspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindAdminByLoginPasswordSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_LOGIN_PASSWORD = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM admins WHERE login=? AND password=?;";
    private String login;
    private String password;
    private ArrayDeque values;

    public FindAdminByLoginPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
        values = new ArrayDeque(2);
        values.push(login);
        values.push(password);
    }

    @Override
    public boolean specify(User entity) {
        return login.equals(entity.getLogin()) && password.equals(entity.getPassword());
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_LOGIN_PASSWORD;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
