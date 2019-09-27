package by.epam.touragency.logic;

import by.epam.touragency.entity.Order;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.entity.Tour;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.OrderRepository;
import by.epam.touragency.repository.impl.TicketRepository;
import by.epam.touragency.repository.impl.TourRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.FindAllOrdersSpecification;
import by.epam.touragency.specification.impl.order.FindClientOrdersSpecification;
import by.epam.touragency.specification.impl.ticket.FindAllTicketsSpecification;
import by.epam.touragency.specification.impl.tour.FindAllToursSpecification;
import by.epam.touragency.specification.impl.tour.FindAvailableToursSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Set;

public class ToPageWithListLogicTest {

    @Test
    public void testGetTicketSetEquals() throws RepositoryException, LogicException {
        Specification specification = new FindAllTicketsSpecification();
        Set<Ticket> expected = TicketRepository.getInstance().query(specification);
        Set<Ticket> actual = ToPageWithListLogic.getTicketSet();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetTourSetForNotAdmin() throws RepositoryException, LogicException {
        Specification specification = new FindAvailableToursSpecification(new Date().getTime());
        Set<Tour> expected = TourRepository.getInstance().query(specification);
        Set<Tour> actual = ToPageWithListLogic.getTourSet("CLIENT");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetTourSetForAdmin() throws RepositoryException, LogicException {
        Specification specification = new FindAllToursSpecification();
        Set<Tour> expected = TourRepository.getInstance().query(specification);
        Set<Tour> actual = ToPageWithListLogic.getTourSet("ADMIN");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetOrderSetForClient() throws RepositoryException, LogicException {
        Specification specification = new FindClientOrdersSpecification(Integer.parseInt("1"));
        Set<Order> expected = OrderRepository.getInstance().query(specification);
        Set<Order> actual = ToPageWithListLogic.getOrderSet("CLIENT", "1");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetOrderSetForNotClietn() throws RepositoryException, LogicException {
        Specification specification = new FindAllOrdersSpecification();
        Set<Order> expected = OrderRepository.getInstance().query(specification);
        Set<Order> actual = ToPageWithListLogic.getOrderSet("AGENT", "1");
        Assert.assertEquals(actual, expected);
    }
}