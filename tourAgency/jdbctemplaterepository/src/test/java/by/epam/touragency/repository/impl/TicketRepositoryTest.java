package by.epam.touragency.repository.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.repository.impl.TicketRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.ticket.AddTicketSpecification;
import by.epam.touragency.specification.impl.ticket.FindAllTicketsSpecification;
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
public class TicketRepositoryTest {
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
    public void getInstance() {
        TicketRepository ticketRepository = TicketRepository.getInstance();
        boolean actual = ticketRepository != null;
        Assert.assertTrue(actual);
    }

    @Test
    public void add()  {
        Ticket ticket = new Ticket.TicketBuilder().setTicketNumber(0).setArrivalCity("test").setArrivalDateTime(1000l).setDepartureCity("test")
                .setDepartureDateTime(1000l).setFlightNumber(0).setId(0).build();
        Specification specification = new AddTicketSpecification(ticket);
        int expected = TicketRepository.getInstance().query(new FindAllTicketsSpecification()).size() + 1;
        TicketRepository.getInstance().add(ticket, specification);
        int actual = TicketRepository.getInstance().query(new FindAllTicketsSpecification()).size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void query() {
        boolean actual = TicketRepository.getInstance().query(new FindAllTicketsSpecification()).isEmpty();
        Assert.assertFalse(actual);
    }
}