package by.epam.touragency.repository.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.*;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.FindAllOrdersSpecification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import junit.framework.Assert;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderRepositoryTest {
    private static Flyway flyway;
    @Mock
    Set set;

    @Mock
    Iterator iterator;

    @Mock
    UserRepository userRepository;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    TourRepository tourRepository;

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    OrderRepository orderRepository;

    @BeforeAll
    public void init() throws IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        EmbeddedPostgres pg = EmbeddedPostgres.builder().setPort(58423).start();
        String url = pg.getJdbcUrl("postgres", "postgres");
        flyway = Flyway.configure().dataSource(url, "postgres", "").load();
        flyway.migrate();
    }

    @AfterAll
    public static void destroy() {
        flyway.clean();
    }

    User client = new User.UserBuilder().setName("test").setSurname("test").setEmail("test@test.com").setLogin("test")
            .setPassword("test").setPhoneNumber("+0000000000").setRole(Role.CLIENT).setStatus("ACTIVE").setId(1).build();
    User agent = new User.UserBuilder().setName("test").setSurname("test").setEmail("test@test.com").setLogin("test")
            .setPassword("test").setPhoneNumber("+0000000000").setRole(Role.AGENT).setStatus("ACTIVE").build();
    Tour tour = new Tour.TourBuilder().setTourName("test").setAdultsNumber(0).setArrivalCity("test").setArrivalCountry("test")
            .setArrivalDate(1000l).setChildrenNumber(0).setDepartureCity("test").setDepartureDate(1000l).setHotel("test")
            .setNutrition("test").setPrice(new BigDecimal(1)).setStatus("AVAILABLE").setId(0).build();
    Ticket ticket = new Ticket.TicketBuilder().setTicketNumber(0).setArrivalCity("test").setArrivalDateTime(1000l).setDepartureCity("test")
            .setDepartureDateTime(1000l).setFlightNumber(0).setId(0).build();

    @Test
    public void query() {
        List<Order> list = new ArrayList<>();
        list.add(new Order.OrderBuilder().build());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(OrderRowMapper.class))).thenReturn(list);
        when(tourRepository.query(any(Specification.class))).thenReturn(set);
        when(ticketRepository.query(any(Specification.class))).thenReturn(set);
        when(userRepository.query(any(Specification.class))).thenReturn(set);
        when(set.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(null);
        boolean actual = orderRepository.query(new FindAllOrdersSpecification()).isEmpty();
        Assert.assertFalse(actual);
    }

    @Test
    public void query_Empty() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(OrderRowMapper.class))).thenReturn(new ArrayList<>());
        boolean actual = orderRepository.query(new FindAllOrdersSpecification()).isEmpty();
        Assert.assertTrue(actual);
    }
}