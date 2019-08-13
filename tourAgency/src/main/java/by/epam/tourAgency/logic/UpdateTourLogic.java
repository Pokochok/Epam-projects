package by.epam.tourAgency.logic;

import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.Repository;
import by.epam.tourAgency.repository.impl.TourRepository;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.tour.*;

import java.math.BigDecimal;

public class UpdateTourLogic {
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

    public static void updateAdultsNumber(int newAdultsNumber, int tourId) throws LogicException {
        Specification specification = new UpdateAdultsNumberByIdSpecification(newAdultsNumber, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updateArrivalCity(String newArrivalCountry, int tourId) throws LogicException {
        Specification specification = new UpdateArrivalCityByIdSpecification(newArrivalCountry, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updateArrivalCountry(String newArrivalCountry, int tourId) throws LogicException {
        Specification specification = new UpdateArrivalCountryByIdSpecification(newArrivalCountry, tourId);

        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updateArrivalDate(long newArrivalDate, int tourId) throws LogicException {
        Specification specification = new UpdateArrivalDateByIdSpecification(newArrivalDate, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updateChildrenNumber(int newChildrenNumber, int tourId) throws LogicException {
        Specification specification = new UpdateChildrenNumberByIdSpecification(newChildrenNumber, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updateDepartureCity(String newDepartureCity, int tourId) throws LogicException {
        Specification specification = new UpdateDepartureCityByIdSpecification(newDepartureCity, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updateDepartureDate(long newDepartureDate, int tourId) throws LogicException {
        Specification specification = new UpdateDepartureDateByIdSpecification(newDepartureDate, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updateHotel(String newHotel, int tourId) throws LogicException {
        Specification specification = new UpdateHotelByIdSpecification(newHotel, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updateNutrition(String newNutrition, int tourId) throws LogicException {
        Specification specification = new UpdateNutritionByIdSpecification(newNutrition, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void updatePrice(BigDecimal newPrice, int tourId) throws LogicException {
        Specification specification = new UpdatePriceByIdSpecification(newPrice, tourId);
        TourRepository repository = TourRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

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
