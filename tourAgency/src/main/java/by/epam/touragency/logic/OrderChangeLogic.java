package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.repository.impl.OrderRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.RemoveOrderByIdSpecification;
import by.epam.touragency.specification.impl.order.UpdatePaymentStateByIdSpecification;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * For order updating logic
 */
@Service
public class OrderChangeLogic {
    @Autowired
    Validation validation;

    /**
     * Set payment state of order as paid
     * @param orderId order ID
     * @throws LogicException if handled RepositoryException
     */
    public boolean payOrder(String orderId) throws LogicException {
        if (!validation.validateId(orderId)){
            return false;
        }
        Specification specification = new UpdatePaymentStateByIdSpecification(Integer.parseInt(orderId));
        try {
            Repository repository = OrderRepository.getInstance();
            repository.update(null, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return true;
    }

    /**
     * Removes order from database
     * @param orderId order ID
     * @throws LogicException if handled RepositoryException
     */
    public boolean removeOrder(String orderId) throws LogicException {
        boolean flag = false;
        try {
            if (validation.validateId(orderId)) {
                Specification specification = new RemoveOrderByIdSpecification(Integer.parseInt(orderId));
                Repository repository = OrderRepository.getInstance();
                repository.remove(null, specification);
                flag = true;
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }
}
