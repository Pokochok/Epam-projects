package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;


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