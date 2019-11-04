package by.epam.touragency.logic;

import by.epam.touragency.config.WebAppTestContext;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import junit.framework.Assert;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MatchOfUniqueFieldsDetectorTest {
    private static Flyway flyway;

    @Autowired
    MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

    @BeforeAll
    public void init() throws IOException, SQLException {
        EmbeddedPostgres pg = EmbeddedPostgres.builder().setPort(58423).start();
        Connection c = pg.getPostgresDatabase().getConnection();
        String url = pg.getJdbcUrl("postgres", "postgres");
        flyway = Flyway.configure().dataSource(url, "postgres", "").load();
        flyway.migrate();
    }

    @AfterAll
    public static void destroy(){
        flyway.clean();
    }

    @Test
    public void testIsExistsEmailExists() {
        boolean actual = matchOfUniqueFieldsDetector.isExistsEmail("not defined");
        Assert.assertTrue(actual);
    }
    @Test
    public void testIsExistsEmailNotExists(){
        boolean actual = matchOfUniqueFieldsDetector.isExistsEmail("rauich@rdb.gru");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsPhoneNumberExists() {
        boolean actual = matchOfUniqueFieldsDetector.isExistsPhoneNumber("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsPhoneNumberNotExists() {
        boolean actual = matchOfUniqueFieldsDetector.isExistsPhoneNumber("+32212345777");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsLoginExists() {
        boolean actual = matchOfUniqueFieldsDetector.isExistsLogin("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsLoginNotExists() {
        boolean actual = matchOfUniqueFieldsDetector.isExistsLogin("garfild");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsTourNameExists() {
        boolean actual = matchOfUniqueFieldsDetector.isExistsTourName("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsTourNameNotExists() {
        boolean actual = matchOfUniqueFieldsDetector.isExistsTourName("not exists");
        Assert.assertFalse(actual);
    }
}