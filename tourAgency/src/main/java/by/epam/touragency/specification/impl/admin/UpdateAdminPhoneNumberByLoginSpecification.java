package by.epam.touragency.specification.impl.admin;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateAdminPhoneNumberByLoginSpecification implements Specification<User> {
    private static final String UPDATE_PHONE_SPECIFICATION_SQL_BY_LOGIN =
            "UPDATE admins SET phone_number=? WHERE login=?;";
    private String phoneNumber;
    private String login;

    public UpdateAdminPhoneNumberByLoginSpecification(String phoneNumber, String login) {
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
        return values;    }
}
