package by.epam.dbworker;

import by.epam.dbworker.connectionpool.ProxyConnectionPool;
import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.repository.impl.TourRepository;
import by.epam.dbworker.specification.Specification;
import by.epam.dbworker.specification.impl.clientspecification.UpdateClientEmailById;
import by.epam.dbworker.specification.impl.tourspecification.AddTourSpecification;
import by.epam.dbworker.specification.impl.tourspecification.FindAllToursSpecification;

import java.math.BigDecimal;
import java.util.Set;

public class DBWorker {

    public static void main(String[] args) {
        /*Tour tour = new Tour("tour in MSK", 100000L, 100_100L, "Minsk", "Moscow",
                "Russia", "Москва", "RO", 2, 1, new BigDecimal(500));
        Repository repository = TourRepository.getInstance();
        Specification specification = new AddTourSpecification(tour);
//        Specification specification = new FindAllToursSpecification();
        repository.add(tour, specification);
//        System.out.println(tours);
        ProxyConnectionPool.getInstance().closePool();
   */ }
}
