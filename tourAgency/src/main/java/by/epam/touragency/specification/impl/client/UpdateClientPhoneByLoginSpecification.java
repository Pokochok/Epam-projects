package by.epam.touragency.specification.impl.client;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientPhoneByLoginSpecification implements Specification<User> {
    private static final String UPDATE_PHONE_SPECIFICATION_SQL_BY_LOGIN =
            "UPDATE clients SET phone_number=? WHERE login=?;";
    private String phoneNumber;
    private String login;

    public UpdateClientPhoneByLoginSpecification(String phoneNumber, String login) {
        this.phoneNumber = phoneNumber;
        this.login = login;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_PHONE_SPECIFICATION_SQL_BY_LOGIN;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(phoneNumber);
        values.push(login);
        return values;
    }
}
