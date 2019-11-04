package by.epam.touragency.logic;

import by.epam.touragency.entity.Order;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.RemoveOrderByDepartureDateSpecification;
import by.epam.touragency.specification.impl.order.UpdatePaymentStateByIdSpecification;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * For order updating logic
 */
@Service
public class OrderChangeLogic {
    @Autowired
    Validation validation;

    @Autowired
    @Qualifier("orderRepository")
    private Repository<Order> orderRepository;

    /**
     * Set payment state of order as paid
     *
     * @param orderId order ID
     */
    public boolean payOrder(String orderId) {
        if (!validation.validateId(orderId)) {
            return false;
        }
        Specification specification = new UpdatePaymentStateByIdSpecification(Integer.parseInt(orderId));
        orderRepository.update(null, specification);
        return true;
    }

    /**
     * Removes order from database
     *
     * @param orderId order ID
     */
    public boolean removeOrder(String orderId) {
        boolean flag = false;
        if (validation.validateId(orderId)) {
            Specification specification = new RemoveOrderByDepartureDateSpecification(Integer.parseInt(orderId));
            orderRepository.remove(null, specification);
            flag = true;
        }
        return flag;
    }
}
