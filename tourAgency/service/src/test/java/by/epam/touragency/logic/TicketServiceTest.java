package by.epam.touragency.logic;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Order;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.FindOrderByTicketIdSpecification;
import by.epam.touragency.specification.impl.ticket.FindTicketByIdSpecification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TicketServiceTest {
    private static Flyway flyway;

    @Mock
    @Qualifier("ticketRepository")
    Repository<Ticket> ticketRepository;

    @Autowired
    @Qualifier("orderRepository")
    Repository<Order> orderRepository;

    @InjectMocks
    TicketService ticketService;

    @BeforeAll
    public void init() throws IOException, SQLException {
        MockitoAnnotations.initMocks(this);
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
    @DisplayName("Transaction failed, expected rollback")
    void testRemoveInvalidTicketsWithOrdersFail() {
        Specification orderSpecification = new FindOrderByTicketIdSpecification(1);
        doThrow(RuntimeException.class).when(ticketRepository).remove(any(), any(Specification.class));
        try {
            ticketService.removeInvalidTicketsWithOrders();
        } catch (RuntimeException ignored) {
            boolean actual = orderRepository.query(orderSpecification).isEmpty();
            assertFalse(actual);
        }
    }

    @Test
    @DisplayName("Transaction success, expected ticket deletion")
    void testRemoveInvalidTicketsWithOrdersSuccess() {
        Specification ticketSpecification = new FindTicketByIdSpecification(1);
        try {
            ticketService.removeInvalidTicketsWithOrders();
        } catch (RuntimeException ignored) {
            boolean actual = ticketRepository.query(ticketSpecification).isEmpty();
            assertTrue(actual);
        }
    }
}