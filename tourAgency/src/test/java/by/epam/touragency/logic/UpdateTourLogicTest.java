package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UpdateTourLogicTest {
    @Autowired
    private UpdateTourLogic updateTourLogic;

    @Test
    public void testUpdateTourNameFalse() throws LogicException {
        boolean actual = updateTourLogic.updateTourName("not defined", -1);
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateTourNameTrue() throws LogicException {
        boolean actual = updateTourLogic.updateTourName("some tour", -1);
        Assert.assertTrue(actual);
    }
}