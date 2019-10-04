package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class AddTourSpecification implements Specification<Tour> {
    private static final String ADD_CLIENT_SQL_ROW = "INSERT INTO tours(tour_name, departure_date, arrival_date, departure_city, " +
            "arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private Tour tour;

    public AddTourSpecification(Tour tour) {
        this.tour = tour;
    }

    @Override
    public String sqlQuery() {
        return ADD_CLIENT_SQL_ROW;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(12);
        values.add(tour.getTourName());
        values.add(tour.getDepartureDateLong());
        values.add(tour.getArrivalDateLong());
        values.add(tour.getDepartureCity());
        values.add(tour.getArrivalCity());
        values.add(tour.getArrivalCountry());
        values.add(tour.getHotel());
        values.add(tour.getNutrition());
        values.add(tour.getAdultsNumber());
        values.add(tour.getChildrenNumber());
        values.add(tour.getPrice());
        values.add(tour.getStatus());
        return values;
    }
}
