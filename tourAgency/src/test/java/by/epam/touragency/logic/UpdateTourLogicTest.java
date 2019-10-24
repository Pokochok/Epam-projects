package by.epam.touragency.logic;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.exception.LogicException;
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
public class UpdateTourLogicTest {
    @Autowired
    private UpdateTourLogic updateTourLogic;

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
    public static void destroy(){
        flyway.clean();
    }

    @Test
    public void testUpdateTourNameFalse() throws LogicException {
        boolean actual = updateTourLogic.updateTourName("not defined", -1);
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateTourNameTrue() throws LogicException {
        boolean actual = updateTourLogic.updateTourName("some tour", -1);
        Assert.assertTrue(actual);
    }
}