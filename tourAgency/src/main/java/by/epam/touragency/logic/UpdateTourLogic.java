package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.TourRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.tour.*;

import java.math.BigDecimal;

/**
 * For tour updates logic
 */
public class UpdateTourLogic {
    /**
     * Updates tour name
     * @param newTourName new tour name
     * @param tourId tour ID
     * @return true if updatin complete successfully, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean updateTourName(String newTourName, int tourId) throws LogicException {
        Specification specificationForValidate = new FindTourByNameSpecification(newTourName);
        Specification specification = new UpdateTourNameByIdSpecification(newTourName, tourId);
        boolean flag = false;
        TourRepository repository = TourRepository.getInstance();
        try {
            if (flag = !repository.isExistsQuery(specificationForValidate)) {
                repository.update(null, specification);
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Updates adults number
     * @param newAdultsNumber new adults number
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateAdultsNumber(int newAdultsNumber, int tourId) throws LogicException {
        Specification specification = new UpdateAdultsNumberByIdSpecification(newAdultsNumber, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates arrival city
     * @param newArrivalCountry new arrival city
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateArrivalCity(String newArrivalCountry, int tourId) throws LogicException {
        Specification specification = new UpdateArrivalCityByIdSpecification(newArrivalCountry, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates arrival country
     * @param newArrivalCountry new arrival country
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateArrivalCountry(String newArrivalCountry, int tourId) throws LogicException {
        Specification specification = new UpdateArrivalCountryByIdSpecification(newArrivalCountry, tourId);

        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates arrival date
     * @param newArrivalDate new arrival date
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateArrivalDate(long newArrivalDate, int tourId) throws LogicException {
        Specification specification = new UpdateArrivalDateByIdSpecification(newArrivalDate, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates children Number
     * @param newChildrenNumber new children number
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateChildrenNumber(int newChildrenNumber, int tourId) throws LogicException {
        Specification specification = new UpdateChildrenNumberByIdSpecification(newChildrenNumber, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates departure city
     * @param newDepartureCity new departure city
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateDepartureCity(String newDepartureCity, int tourId) throws LogicException {
        Specification specification = new UpdateDepartureCityByIdSpecification(newDepartureCity, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates departude date
     * @param newDepartureDate new departure date
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateDepartureDate(long newDepartureDate, int tourId) throws LogicException {
        Specification specification = new UpdateDepartureDateByIdSpecification(newDepartureDate, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates hotel
     * @param newHotel new hotel
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateHotel(String newHotel, int tourId) throws LogicException {
        Specification specification = new UpdateHotelByIdSpecification(newHotel, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates nutrition
     * @param newNutrition new nutrition
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateNutrition(String newNutrition, int tourId) throws LogicException {
        Specification specification = new UpdateNutritionByIdSpecification(newNutrition, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates price
     * @param newPrice new price
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updatePrice(BigDecimal newPrice, int tourId) throws LogicException {
        Specification specification = new UpdatePriceByIdSpecification(newPrice, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Updates tour status
     * @param status tour status
     * @param tourId tour ID
     * @throws LogicException if handled RepositoryException
     */
    public static void updateStatus(String status, int tourId) throws LogicException {
        Specification specification = new UpdateStatusByIdSpecification(status, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }
}
