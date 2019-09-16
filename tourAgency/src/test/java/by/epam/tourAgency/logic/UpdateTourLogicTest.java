package by.epam.tourAgency.logic;

import by.epam.tourAgency.exception.LogicException;
import org.junit.Assert;
import org.junit.Test;

public class UpdateTourLogicTest {

    @Test
    public void testUpdateTourNameFalse() throws LogicException {
        boolean actual = UpdateTourLogic.updateTourName("not defined", -1);
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateTourNameTrue() throws LogicException {
        boolean actual = UpdateTourLogic.updateTourName("some tour", -1);
        Assert.assertTrue(actual);
    }
}