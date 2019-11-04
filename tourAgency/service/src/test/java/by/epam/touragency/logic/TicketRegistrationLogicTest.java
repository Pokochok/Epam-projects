package by.epam.touragency.logic;

import by.epam.touragency.config.WebAppTestContext;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import junit.framework.Assert;
import org.flywaydb.core.Flyway;
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
public class TicketRegistrationLogicTest {
    private static Flyway flyway;

    @Autowired
    TicketRegistrationLogic ticketRegistrationLogic;

    @BeforeAll
    public void init() throws IOException, SQLException {
        EmbeddedPostgres pg = EmbeddedPostgres.builder().setPort(58423).start();
        Connection c = pg.getPostgresDatabase().getConnection();
        String url = pg.getJdbcUrl("postgres", "postgres");
        flyway = Flyway.configure().dataSource(url, "postgres", "").load();
        flyway.migrate();
    }

    @Test
    public void testIsTicketExistsTrue() {
        boolean actual = ticketRegistrationLogic.isTicketExists("0", "0",
                "not defined", "not defined", 0L, 0L);
        Assert.assertTrue(actual);
    }
    @Test
    public void testIsTicketExistsFalse() {
        boolean actual = ticketRegistrationLogic.isTicketExists("1", "1",
                "not defined", "not defined", 1L, 1L);
        Assert.assertFalse(actual);
    }

}