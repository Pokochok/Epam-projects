package by.epam.touragency.logic;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.connectionpool.PropertyHolder;
import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.User;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.FindAgentByLoginPasswordSpecification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import junit.framework.Assert;
import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@ContextConfiguration(classes = {TestContext.class, WebAppTestContext.class})
@WebAppConfiguration
public class LoginLogicTest {
    @InjectMocks
    private LoginLogic loginLogic;


    private static Flyway flyway;


    @BeforeAll
    public static void initDb() throws IOException, SQLException {
        EmbeddedPostgres pg = EmbeddedPostgres.start();
        Connection c = pg.getPostgresDatabase().getConnection();
        String url = pg.getJdbcUrl("postgres", "postgres");
        PropertyHolder propertyHolder = PropertyHolder.getInstance(url);
        flyway = Flyway.configure().dataSource(url, "postgres", "").load();
        flyway.migrate();
    }

    @AfterAll
    public static void destroy(){
        flyway.clean();
    }

    User client = new User.UserBuilder().setName("test").setSurname("test").setEmail("test@test.com").setLogin("test")
            .setPassword("test").setPhoneNumber("+0000000000").setRole(Role.CLIENT).setStatus("ACTIVE").setId(1).build();

    @Test
    public void testCheckLoginPasswordExists() throws LogicException, RepositoryException {
        Specification specification = new FindAgentByLoginPasswordSpecification("not defined",
                BCrypt.hashpw("1234567890", BCrypt.gensalt()));
        User expected = UserRepository.getInstance().query(specification).iterator().next();
    }

    @Test
    public void testCheckLoginPasswordNotExists() throws LogicException, RepositoryException {
        User actual = new LoginLogic().checkLoginPassword("grekovaAnn", "123456789340");
        Assert.assertNull(actual);
    }
}