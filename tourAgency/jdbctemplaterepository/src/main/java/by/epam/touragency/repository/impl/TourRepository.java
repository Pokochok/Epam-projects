package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.entity.TourRowMapper;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Repository
public class TourRepository implements Repository<Tour> {
    private static final Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private static TourRepository tourRepository;

    private TourRepository() {
    }

    @Autowired
    public TourRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        tourRepository = this;
    }

    public static TourRepository getInstance() {
        if (tourRepository != null) {
            return tourRepository;
        } else {
            tourRepository = new TourRepository();
            return tourRepository;
        }
    }

    @Override
    public void add(Tour tour, Specification specification) {
        jdbcTemplate.update(specification.sqlQuery(), tour.getTourName(), tour.getDepartureDateLong(),
                tour.getArrivalDateLong(), tour.getDepartureCity(), tour.getArrivalCity(), tour.getArrivalCountry(),
                tour.getHotel(), tour.getNutrition(), tour.getAdultsNumber(), tour.getChildrenNumber(), tour.getPrice(),
                tour.getStatus());
    }

    @Override
    public void update(Tour entity, Specification specification) {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public void remove(Tour entity, Specification specification) {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public Set<Tour> query(Specification specification) {
        return new HashSet<>(jdbcTemplate.query(specification.sqlQuery(), specification.getParameterQueue().toArray(),
                new TourRowMapper()));
    }

    @Override
    public boolean isExistsQuery(Specification specification) {
        try {
            return !new HashSet<>(jdbcTemplate.query(specification.sqlQuery(), specification.getParameterQueue().toArray(),
                    new TourRowMapper())).isEmpty();
        }catch (RuntimeException e){
            LOGGER.error("Error while getting is exists query ");
            throw new RuntimeException("Error while getting is exists query ", e);
        }
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
