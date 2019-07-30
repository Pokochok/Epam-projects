package by.epam.dbworker.specification.impl.tourspecification;

import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.specification.Specification;

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
    public boolean specify(Tour entity) {
        return false;
    }

    @Override
    public String sqlQuery() {
        return ADD_CLIENT_SQL_ROW;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        ArrayDeque values = new ArrayDeque(12);
        values.push(tour.getTourName());
        values.push(tour.getDepartureDateLong());
        values.push(tour.getArrivalDateLong());
        values.push(tour.getDepartureCity());
        values.push(tour.getArrivalCity());
        values.push(tour.getArrivalCountry());
        values.push(tour.getHotel());
        values.push(tour.getNutrition());
        values.push(tour.getAdultsNumber());
        values.push(tour.getChildrenNumber());
        values.push(tour.getPrice());
        values.push(tour.getStatus());
        return values;
    }
}
