package by.epam.tourAgency.logic;

import by.epam.tourAgency.entity.Tour;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.impl.TourRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.tour.AddTourSpecification;
import by.epam.tourAgency.specification.impl.tour.FindTourByNameSpecification;

import java.math.BigDecimal;

/**
 * For tour registration logic
 */
public class TourRegistrationLogic {
    /**
     * Adds tour to database
     * @param tourName tour name
     * @param departureDate departure date
     * @param arrivalDate arrival date
     * @param departureCity departure city
     * @param arrivalCity arrival city
     * @param arrivalCountry arrival country
     * @param hotel hotel
     * @param nutrition nutrition
     * @param numberOfAdults adults number
     * @param numberOfChildren children number
     * @param price tour price
     * @param isAvailable tour status
     * @throws LogicException if handled RepositoryException
     */
    public static void addTour(String tourName, long departureDate, long arrivalDate, String departureCity, String arrivalCity,
                               String arrivalCountry, String hotel, String nutrition, int numberOfAdults, int numberOfChildren,
                               BigDecimal price, String isAvailable) throws LogicException {
        Tour tour = new Tour.TourBuilder()
        .setTourName(tourName)
                .setDepartureDate(departureDate)
                .setArrivalDate(arrivalDate)
                .setDepartureCity(departureCity)
                .setArrivalCity(arrivalCity)
                .setArrivalCountry(arrivalCountry)
                .setHotel(hotel)
                .setNutrition(nutrition)
                .setAdultsNumber(numberOfAdults)
                .setChildrenNumber(numberOfChildren)
                .setPrice(price)
                .setStatus(isAvailable). build();
        Specification specification = new AddTourSpecification(tour);
        try {
            TourRepository.getInstance().add(tour, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }
}
