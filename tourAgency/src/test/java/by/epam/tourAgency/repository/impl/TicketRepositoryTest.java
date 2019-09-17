package by.epam.tourAgency.repository.impl;

import by.epam.tourAgency.connectionpool.PropertyHolder;
import by.epam.tourAgency.entity.Ticket;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.ticket.AddTicketSpecification;
import by.epam.tourAgency.specification.impl.ticket.FindAllTicketsSpecification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TicketRepositoryTest {
    @BeforeClass
    public static void initDb() throws IOException, SQLException {
        EmbeddedPostgres pg = EmbeddedPostgres.start();
        Connection c = pg.getPostgresDatabase().getConnection();
        String url = pg.getJdbcUrl("postgres", "postgres");
        PropertyHolder propertyHolder = PropertyHolder.getInstance(url);
        Flyway flyway = Flyway.configure().dataSource(url, "postgres", "111222").load();
        flyway.migrate();
    }

    @Test
    public void getInstance() {
        TicketRepository ticketRepository = TicketRepository.getInstance();
        boolean actual = ticketRepository != null;
        Assert.assertTrue(actual);
    }

    @Test
    public void add() throws RepositoryException {
        Ticket ticket = new Ticket.TicketBuilder().setTicketNumber(0).setArrivalCity("test").setArrivalDateTime(1000l).setDepartureCity("test")
                .setDepartureDateTime(1000l).setFlightNumber(0).setId(0).build();
        Specification specification = new AddTicketSpecification(ticket);
        int expected = TicketRepository.getInstance().query(new FindAllTicketsSpecification()).size() + 1;
        TicketRepository.getInstance().add(ticket, specification);
        int actual = TicketRepository.getInstance().query(new FindAllTicketsSpecification()).size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void query() throws RepositoryException {
        boolean actual = TicketRepository.getInstance().query(new FindAllTicketsSpecification()).isEmpty();
        Assert.assertFalse(actual);
    }
}