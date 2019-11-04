package by.epam.touragency.logic;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.entity.Tour;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.FindAllOrdersSpecification;
import by.epam.touragency.specification.impl.order.FindClientOrdersSpecification;
import by.epam.touragency.specification.impl.ticket.FindAllTicketsSpecification;
import by.epam.touragency.specification.impl.tour.FindAllToursSpecification;
import by.epam.touragency.specification.impl.tour.FindAvailableToursSpecification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ToPageWithListLogicTest {

    @Autowired
    private ToPageWithListLogic toPageWithListLogic;

    @Autowired
    @Qualifier("orderRepository")
    private Repository<Order> orderRepository;

    @Autowired
    @Qualifier("ticketRepository")
    private Repository<Ticket> ticketRepository;

    @Autowired
    @Qualifier("tourRepository")
    private Repository<Tour> tourRepository;

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
    public void testGetTicketSetEquals() {
        Specification specification = new FindAllTicketsSpecification();
        Set<Ticket> expected = ticketRepository.query(specification);
        Set<Ticket> actual = toPageWithListLogic.getTicketSet();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetTourSetForNotAdmin() {
        Specification specification = new FindAvailableToursSpecification(new Date().getTime());
        Set<Tour> expected = tourRepository.query(specification);
        Set<Tour> actual = toPageWithListLogic.getTourSet("CLIENT");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetTourSetForAdmin() {
        Specification specification = new FindAllToursSpecification();
        Set<Tour> expected = tourRepository.query(specification);
        Set<Tour> actual = toPageWithListLogic.getTourSet("ADMIN");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetOrderSetForClient() {
        Specification specification = new FindClientOrdersSpecification(Integer.parseInt("1"));
        int expected = orderRepository.query(specification).size();
        int actual = toPageWithListLogic.getOrderSet("CLIENT", "1").size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetOrderSetForNotClient() {
        Specification specification = new FindAllOrdersSpecification();
        int expected = orderRepository.query(specification).size();
        int actual = toPageWithListLogic.getOrderSet("AGENT", "1").size();
        Assertions.assertEquals(expected, actual);
    }
}