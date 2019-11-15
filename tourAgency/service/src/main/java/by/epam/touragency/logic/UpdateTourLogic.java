package by.epam.touragency.logic;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.tour.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * For tour updates logic
 */
@Service
public class UpdateTourLogic {
    @Autowired
    @Qualifier("hibernateTourRepository")
    private Repository<Tour> tourRepository;

    /**
     * Updates tour name
     *
     * @param newTourName new tour name
     * @param tourId      tour ID
     * @return true if updatin complete successfully, and false - if not
     */
    public boolean updateTourName(String newTourName, int tourId, Tour tour) {
        Specification specificationForValidate = new FindTourByNameSpecification(newTourName);
        boolean flag = !tourRepository.isExistsQuery(specificationForValidate);
        if (flag) {
            tour.setTourName(newTourName);
            Specification specification = new UpdateTourNameByIdSpecification(newTourName, tourId);
            tourRepository.update(tour, specification);
        }
        return flag;
    }

    /**
     * Updates adults number
     *
     * @param newAdultsNumber new adults number
     * @param tourId          tour ID
     */
    public void updateAdultsNumber(int newAdultsNumber, int tourId, Tour tour) {
            Specification specification = new UpdateAdultsNumberByIdSpecification(newAdultsNumber, tourId);
            tour.setAdultsNumber(newAdultsNumber);
            tourRepository.update(tour, specification);
    }

    /**
     * Updates arrival city
     *
     * @param newArrivalCity new arrival city
     * @param tourId            tour ID
     */
    public void updateArrivalCity(String newArrivalCity, int tourId, Tour tour) {
        tour.setArrivalCity(newArrivalCity);
        Specification specification = new UpdateArrivalCityByIdSpecification(newArrivalCity, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates arrival country
     *
     * @param newArrivalCountry new arrival country
     * @param tourId            tour ID
     */
    public void updateArrivalCountry(String newArrivalCountry, int tourId, Tour tour) {
        tour.setArrivalCountry(newArrivalCountry);
        Specification specification = new UpdateArrivalCountryByIdSpecification(newArrivalCountry, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates arrival date
     *
     * @param newArrivalDate new arrival date
     * @param tourId         tour ID
     */
    public void updateArrivalDate(long newArrivalDate, int tourId, Tour tour) {
        tour.setArrivalDate(newArrivalDate);
        Specification specification = new UpdateArrivalDateByIdSpecification(newArrivalDate, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates children Number
     *
     * @param newChildrenNumber new children number
     * @param tourId            tour ID
     */
    public void updateChildrenNumber(int newChildrenNumber, int tourId, Tour tour) {
        tour.setChildrenNumber(newChildrenNumber);
        Specification specification = new UpdateChildrenNumberByIdSpecification(newChildrenNumber, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates departure city
     *
     * @param newDepartureCity new departure city
     * @param tourId           tour ID
     */
    public void updateDepartureCity(String newDepartureCity, int tourId, Tour tour) {
        tour.setDepartureCity(newDepartureCity);
        Specification specification = new UpdateDepartureCityByIdSpecification(newDepartureCity, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates departude date
     *
     * @param newDepartureDate new departure date
     * @param tourId           tour ID
     */
    public void updateDepartureDate(long newDepartureDate, int tourId, Tour tour) {
        tour.setDepartureDate(newDepartureDate);
        Specification specification = new UpdateDepartureDateByIdSpecification(newDepartureDate, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates hotel
     *
     * @param newHotel new hotel
     * @param tourId   tour ID
     */
    public void updateHotel(String newHotel, int tourId, Tour tour) {
        tour.setHotel(newHotel);
        Specification specification = new UpdateHotelByIdSpecification(newHotel, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates nutrition
     *
     * @param newNutrition new nutrition
     * @param tourId       tour ID
     */
    public void updateNutrition(String newNutrition, int tourId, Tour tour) {
        tour.setNutrition(newNutrition);
        Specification specification = new UpdateNutritionByIdSpecification(newNutrition, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates price
     *
     * @param newPrice new price
     * @param tourId   tour ID
     */
    public void updatePrice(BigDecimal newPrice, int tourId,  Tour tour) {
        tour.setPrice(newPrice);
        Specification specification = new UpdatePriceByIdSpecification(newPrice, tourId);
        tourRepository.update(tour, specification);
    }

    /**
     * Updates tour status
     *
     * @param status tour status
     * @param tourId tour ID
     */
    public void updateStatus(String status, int tourId, Tour tour) {
        tour.setStatus(status);
        Specification specification = new UpdateStatusByIdSpecification(status, tourId);
        tourRepository.update(tour, specification);
    }
}
