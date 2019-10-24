package by.epam.touragency.repository.impl;

import by.epam.touragency.config.PropertyHolder;
import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.User;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.AddAgentSpecification;
import by.epam.touragency.specification.impl.agent.FindAgentByLoginSpecification;
import by.epam.touragency.specification.impl.agent.UpdateAgentNameByLoginSpecification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import junit.framework.Assert;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;


import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    private static Flyway flyway;

    @BeforeAll
    public void init() throws IOException, SQLException {
        EmbeddedPostgres pg = EmbeddedPostgres.builder().setPort(58423).start();
        Connection c = pg.getPostgresDatabase().getConnection();
        String url = pg.getJdbcUrl("postgres", "postgres");
        flyway = Flyway.configure().dataSource(url, "postgres", "").load();
        flyway.migrate();
    }

    @AfterAll
    public static void destroy() {
        flyway.clean();
    }

    @Test
    public void getInstance() {
        UserRepository userRepository = UserRepository.getInstance();
        boolean actual = userRepository != null;
        Assert.assertTrue(actual);
    }

    @Test
    public void add() throws RepositoryException {
        User user = new User.UserBuilder().setName("test").setSurname("test").setEmail("test@test.com").setLogin("test")
                .setPassword("test").setPhoneNumber("+0000000000").setRole(Role.AGENT).setStatus("ACTIVE").build();
        Specification specification = new AddAgentSpecification(user);
        UserRepository.getInstance().add(user, specification);
        String expected = "test";
        String actual = UserRepository.getInstance().query(new FindAgentByLoginSpecification("test")).iterator().next().getLogin();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws RepositoryException {
        User user = new User.UserBuilder().setName("test2").setSurname("test2").setEmail("test2@test.com").setLogin("test2")
                .setPassword("test2").setPhoneNumber("+0000000000").setRole(Role.AGENT).setStatus("ACTIVE").build();
        Specification specification = new AddAgentSpecification(user);
        UserRepository.getInstance().add(user, specification);
        String expected = "test2updated";
        UserRepository.getInstance().update(user, new UpdateAgentNameByLoginSpecification("test2updated", "test2"));
        String actual = UserRepository.getInstance().query(new FindAgentByLoginSpecification("test2")).iterator().next().getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void query() throws RepositoryException {
        int expected = 0;
        int actual = UserRepository.getInstance().query(new FindAgentByLoginSpecification("not defined")).iterator().next().getId();
        Assert.assertEquals(expected, actual);
    }
}