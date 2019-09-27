package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateHotelByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_HOTEL_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET hotel=? WHERE id=?;";
    private String hotel;
    private int id;

    public UpdateHotelByIdSpecification(String hotel, int id) {
        this.hotel = hotel;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_HOTEL_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(hotel);
        values.push(id);
        return values;
    }
}
