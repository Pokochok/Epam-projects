package by.epam.touragency.logic;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.TourRepository;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.tour.AddTourSpecification;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

import static by.epam.touragency.util.PageMsgConstant.TO_TOUR_REGISTRATION_PAGE_PATH;

/**
 * For tour registration logic
 */
@Service
public class TourRegistrationLogic {
    @Autowired
    private Validation validation;
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
    public void addTour(String tourName, long departureDate, long arrivalDate, String departureCity, String arrivalCity,
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

    public boolean isValidData(String tourName, String departureCity, String arrivalCity, String arrivalCountry,
                                      String hotel, String nutrition, String childrenNumber, String adultsNumber,
                                      String price){
         return validation.validateName(tourName) && validation.validateTourStringItems(departureCity) &&
                validation.validateTourStringItems(arrivalCity) && validation.validateTourStringItems(arrivalCountry) &&
                validation.validateTourStringItems(hotel) && validation.validateNutrition(nutrition) &&
                validation.validateNumberOfPeople(childrenNumber) &&
                validation.validateNumberOfPeople(adultsNumber) &&
                validation.validatePrice(price);
    }
}
