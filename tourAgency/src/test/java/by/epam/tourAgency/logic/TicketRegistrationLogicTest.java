package by.epam.tourAgency.logic;

import by.epam.tourAgency.exception.LogicException;
import org.junit.Assert;
import org.junit.Test;

public class TicketRegistrationLogicTest {

    @Test
    public void testIsTicketExistsTrue() throws LogicException {
        boolean actual = TicketRegistrationLogic.isTicketExists("0", "0",
                "not defined", "not defined", 0L, 0L);
        Assert.assertTrue(actual);
    }
    @Test
    public void testIsTicketExistsFalse() throws LogicException {
        boolean actual = TicketRegistrationLogic.isTicketExists("1", "1",
                "not defined", "not defined", 1L, 1L);
        Assert.assertFalse(actual);
    }

}