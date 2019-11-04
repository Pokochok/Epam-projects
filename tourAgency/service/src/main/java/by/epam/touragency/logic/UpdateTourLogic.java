package by.epam.touragency.logic;

import by.epam.touragency.repository.impl.TourRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.tour.*;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * For tour updates logic
 */
@Service
public class UpdateTourLogic {
    /**
     * Updates tour name
     *
     * @param newTourName new tour name
     * @param tourId      tour ID
     * @return true if updatin complete successfully, and false - if not
     */
    public boolean updateTourName(String newTourName, int tourId) {
        Specification specificationForValidate = new FindTourByNameSpecification(newTourName);
        Specification specification = new UpdateTourNameByIdSpecification(newTourName, tourId);
        boolean flag = false;
        TourRepository repository = TourRepository.getInstance();
        if (flag = !repository.isExistsQuery(specificationForValidate)) {
            repository.update(null, specification);
        }
        return flag;
    }

    /**
     * Updates adults number
     *
     * @param newAdultsNumber new adults number
     * @param tourId          tour ID
     */
    public void updateAdultsNumber(int newAdultsNumber, int tourId) {
        Specification specification = new UpdateAdultsNumberByIdSpecification(newAdultsNumber, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates arrival city
     *
     * @param newArrivalCountry new arrival city
     * @param tourId            tour ID
     */
    public void updateArrivalCity(String newArrivalCountry, int tourId) {
        Specification specification = new UpdateArrivalCityByIdSpecification(newArrivalCountry, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates arrival country
     *
     * @param newArrivalCountry new arrival country
     * @param tourId            tour ID
     */
    public void updateArrivalCountry(String newArrivalCountry, int tourId) {
        Specification specification = new UpdateArrivalCountryByIdSpecification(newArrivalCountry, tourId);

        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates arrival date
     *
     * @param newArrivalDate new arrival date
     * @param tourId         tour ID
     */
    public void updateArrivalDate(long newArrivalDate, int tourId) {
        Specification specification = new UpdateArrivalDateByIdSpecification(newArrivalDate, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates children Number
     *
     * @param newChildrenNumber new children number
     * @param tourId            tour ID
     */
    public void updateChildrenNumber(int newChildrenNumber, int tourId) {
        Specification specification = new UpdateChildrenNumberByIdSpecification(newChildrenNumber, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates departure city
     *
     * @param newDepartureCity new departure city
     * @param tourId           tour ID
     */
    public void updateDepartureCity(String newDepartureCity, int tourId) {
        Specification specification = new UpdateDepartureCityByIdSpecification(newDepartureCity, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates departude date
     *
     * @param newDepartureDate new departure date
     * @param tourId           tour ID
     */
    public void updateDepartureDate(long newDepartureDate, int tourId) {
        Specification specification = new UpdateDepartureDateByIdSpecification(newDepartureDate, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates hotel
     *
     * @param newHotel new hotel
     * @param tourId   tour ID
     */
    public void updateHotel(String newHotel, int tourId) {
        Specification specification = new UpdateHotelByIdSpecification(newHotel, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates nutrition
     *
     * @param newNutrition new nutrition
     * @param tourId       tour ID
     */
    public void updateNutrition(String newNutrition, int tourId) {
        Specification specification = new UpdateNutritionByIdSpecification(newNutrition, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates price
     *
     * @param newPrice new price
     * @param tourId   tour ID
     */
    public void updatePrice(BigDecimal newPrice, int tourId) {
        Specification specification = new UpdatePriceByIdSpecification(newPrice, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }

    /**
     * Updates tour status
     *
     * @param status tour status
     * @param tourId tour ID
     */
    public void updateStatus(String status, int tourId) {
        Specification specification = new UpdateStatusByIdSpecification(status, tourId);
        TourRepository repository = TourRepository.getInstance();
        repository.update(null, specification);
    }
}
