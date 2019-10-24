package by.epam.touragency.repository.impl;

import by.epam.touragency.config.PropertyHolder;
import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Tour;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.tour.AddTourSpecification;
import by.epam.touragency.specification.impl.tour.FindAllToursSpecification;
import by.epam.touragency.specification.impl.tour.FindTourByNameSpecification;
import by.epam.touragency.specification.impl.tour.UpdateChildrenNumberByIdSpecification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import junit.framework.Assert;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TourRepositoryTest {
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

    Tour tour = new Tour.TourBuilder().setTourName("test").setAdultsNumber(0).setArrivalCity("test").setArrivalCountry("test")
            .setArrivalDate(1000l).setChildrenNumber(0).setDepartureCity("test").setDepartureDate(1000l).setHotel("test")
            .setNutrition("test").setPrice(new BigDecimal(1)).setStatus("AVAILABLE").setId(0).build();

    @Test
    public void getInstance() {
        TourRepository tourRepository = TourRepository.getInstance();
        boolean actual = tourRepository != null;
        Assert.assertTrue(actual);
    }

    @Test
    public void add() throws RepositoryException {
        Specification specification = new AddTourSpecification(tour);
        int expected = TourRepository.getInstance().query(new FindAllToursSpecification()).size() + 1;
        TourRepository.getInstance().add(tour, specification);
        int actual = TourRepository.getInstance().query(new FindAllToursSpecification()).size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws RepositoryException {
        Specification specification = new AddTourSpecification(tour);
        TourRepository.getInstance().add(tour, specification);
        int id = TourRepository.getInstance().query(new FindTourByNameSpecification("test")).iterator().next().getId();
        TourRepository.getInstance().update(tour, new UpdateChildrenNumberByIdSpecification(5, id));
        int expected = 5;
        int actual = TourRepository.getInstance().query(new FindTourByNameSpecification("test")).iterator().next().getChildrenNumber();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void query() throws RepositoryException {
        boolean actual = TourRepository.getInstance().query(new FindTourByNameSpecification("test")).isEmpty();
        Assert.assertFalse(actual);
    }
}