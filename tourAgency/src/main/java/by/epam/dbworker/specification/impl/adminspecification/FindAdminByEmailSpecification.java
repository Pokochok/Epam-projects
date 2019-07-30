package by.epam.dbworker.specification.impl.adminspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindAdminByEmailSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_EMAIL = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM admins WHERE email=?;";
    private ArrayDeque values;
    private String email;

    public FindAdminByEmailSpecification(String email) {
        this.email = email;
        values = new ArrayDeque();
        values.push(email);
    }

    @Override
    public boolean specify(User entity) {
        return email.equals(entity.getEmail());
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_EMAIL;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
