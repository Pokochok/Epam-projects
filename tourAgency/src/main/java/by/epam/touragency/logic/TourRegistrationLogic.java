package by.epam.touragency.logic;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.TourRepository;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.tour.AddTourSpecification;
import by.epam.touragency.util.Validation;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

import static by.epam.touragency.util.PageMsgConstant.TO_TOUR_REGISTRATION_PAGE_PATH;

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

    public static boolean isValidData(String tourName, String departureCity, String arrivalCity, String arrivalCountry,
                                      String hotel, String nutrition, String childrenNumber, String adultsNumber,
                                      String price){
         return Validation.validateName(tourName) && Validation.validateTourStringItems(departureCity) &&
                Validation.validateTourStringItems(arrivalCity) && Validation.validateTourStringItems(arrivalCountry) &&
                Validation.validateTourStringItems(hotel) && Validation.validateNutrition(nutrition) &&
                Validation.validateNumberOfPeople(childrenNumber) &&
                Validation.validateNumberOfPeople(adultsNumber) &&
                Validation.validatePrice(price);
    }
}
