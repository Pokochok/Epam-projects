package by.epam.tourAgency.logic;

import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.Repository;
import by.epam.tourAgency.repository.impl.OrderRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.order.RemoveOrderByIdSpecification;
import by.epam.tourAgency.specification.impl.order.UpdatePaymentStateByIdSpecification;

public class OrderChangeLogic {
    public static void payOrder(int orderId) throws LogicException {
        Specification specification = new UpdatePaymentStateByIdSpecification(orderId);
        Repository repository = OrderRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    public static void removeOrder(int orderId) throws LogicException {
        Specification specification = new RemoveOrderByIdSpecification(orderId);
        Repository repository = OrderRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }
}
