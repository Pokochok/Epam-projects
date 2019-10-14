package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketRegistrationLogicTest {
    @Autowired
    TicketRegistrationLogic ticketRegistrationLogic;

    @Test
    public void testIsTicketExistsTrue() throws LogicException {
        boolean actual = ticketRegistrationLogic.isTicketExists("0", "0",
                "not defined", "not defined", 0L, 0L);
        Assert.assertTrue(actual);
    }
    @Test
    public void testIsTicketExistsFalse() throws LogicException {
        boolean actual = ticketRegistrationLogic.isTicketExists("1", "1",
                "not defined", "not defined", 1L, 1L);
        Assert.assertFalse(actual);
    }

}