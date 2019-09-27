package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.repository.impl.OrderRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.RemoveOrderByIdSpecification;
import by.epam.touragency.specification.impl.order.UpdatePaymentStateByIdSpecification;

/**
 * For order updating logic
 */
public class OrderChangeLogic {
    /**
     * Set payment state of order as paid
     * @param orderId order ID
     * @throws LogicException if handled RepositoryException
     */
    public static void payOrder(int orderId) throws LogicException {
        Specification specification = new UpdatePaymentStateByIdSpecification(orderId);
        Repository repository = OrderRepository.getInstance();
        try {
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Removes order from database
     * @param orderId order ID
     * @throws LogicException if handled RepositoryException
     */
    public static void removeOrder(int orderId) throws LogicException {
        Specification specification = new RemoveOrderByIdSpecification(orderId);
        Repository repository = OrderRepository.getInstance();
        try {
            repository.remove(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }
}
