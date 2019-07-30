package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientPhoneByLogin implements Specification<User> {
    private static final String UPDATE_PHONE_SPECIFICATION_SQL_BY_LOGIN =
            "UPDATE clients SET phone_number=? WHERE login=?;";
    private String phoneNumber;
    private String login;

    public UpdateClientPhoneByLogin(String phoneNumber, String login) {
        this.phoneNumber = phoneNumber;
        this.login = login;
    }

    @Override
    public boolean specify(User entity) {
        return phoneNumber.equals(entity.getPhoneNumber());
    }

    @Override
    public String sqlQuery() {
        return UPDATE_PHONE_SPECIFICATION_SQL_BY_LOGIN;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        ArrayDeque values = new ArrayDeque(2);
        values.push(phoneNumber);
        values.push(login);
        return values;
    }
}
