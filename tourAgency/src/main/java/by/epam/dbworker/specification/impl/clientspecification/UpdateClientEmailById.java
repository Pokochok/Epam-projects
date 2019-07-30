package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientEmailById implements Specification<User> {
    private static final String UPDATE_EMAIL_SPECIFICATION_SQL_BY_ID =
            "UPDATE clients SET email=? WHERE id=?;";
    private String email;
    private int id;
    private ArrayDeque values;

    public UpdateClientEmailById(String email, int id) {
        this.email = email;
        this.id = id;
        values = new ArrayDeque(2);
        values.push(email);
        values.push(id);
    }

    @Override
    public boolean specify(User entity) {
        return email.equals(entity.getEmail());
    }

    @Override
    public String sqlQuery() {
        return UPDATE_EMAIL_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
