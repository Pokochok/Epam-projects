package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.User;
import by.epam.touragency.entity.UserRowMapper;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User> {

    private JdbcTemplate jdbcTemplate;
    private static UserRepository userRepository;

    private UserRepository() {
    }

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userRepository = this;
    }

    public static UserRepository getInstance() {
        if (userRepository != null) {
            return userRepository;
        } else {
            userRepository = new UserRepository();
            return userRepository;
        }
    }

    @Override
    public void add(User user, Specification specification) throws RepositoryException {
        jdbcTemplate.update(specification.sqlQuery(), user.getName(), user.getSurname(), user.getEmail(),
                user.getPhoneNumber(), user.getLogin(), user.getPassword(), user.getRole().toString());
    }

    @Override
    public void update(User user, Specification specification) throws RepositoryException {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public void remove(User user, Specification specification) throws RepositoryException {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public Set<User> query(Specification specification) throws RepositoryException {
        return new HashSet<>(jdbcTemplate.query(specification.sqlQuery(), specification.getParameterQueue().toArray(),
                new UserRowMapper()));
    }

    @Override
    public boolean isExistsQuery(Specification specification) throws RepositoryException {
        return !jdbcTemplate.query(specification.sqlQuery(), specification.getParameterQueue().toArray(),
                new UserRowMapper()).isEmpty();
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
