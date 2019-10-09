package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;


public class UpdateUserLogicTest {

    @Test
    public void testUpdateEmailTrue() throws LogicException {
        boolean actual = UpdateUserLogic.updateEmail("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        UpdateUserLogic.updateEmail("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdateEmailFalse() throws LogicException {
        boolean actual = UpdateUserLogic.updateEmail("AGENT", "not defined", "login");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdatePhoneNumberTrue() throws LogicException {
        boolean actual = UpdateUserLogic.updatePhoneNumber("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        UpdateUserLogic.updatePhoneNumber("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdatePhoneNumberFalse() throws LogicException {
        boolean actual = UpdateUserLogic.updatePhoneNumber("AGENT", "not defined", "login");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateLoginTrue() throws LogicException {
        boolean actual = UpdateUserLogic.updateLogin("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        UpdateUserLogic.updateLogin("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdateLoginFalse() throws LogicException {
        boolean actual = UpdateUserLogic.updateLogin("AGENT", "not defined", "email");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdatePasswordTrue() throws LogicException {
        boolean actual = UpdateUserLogic.updatePassword("AGENT", "not defined", "1234567890", "1234567890");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdatePasswordFalse() throws LogicException {
        boolean actual = UpdateUserLogic.updatePassword("AGENT", "not defined", "pass", "not defined");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateNameTrue() throws LogicException {
        boolean actual = UpdateUserLogic.updateName("AGENT", "not defined", "not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdateNameFalse() throws LogicException {
        boolean actual = UpdateUserLogic.updateName("AGENT", "login", "not defined");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateSurnameTrue() throws LogicException {
        boolean actual = UpdateUserLogic.updateSurname("AGENT", "not defined", "not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdateSurnameFalse() throws LogicException {
        boolean actual = UpdateUserLogic.updateSurname("AGENT", "login", "not defined");
        Assert.assertFalse(actual);
    }
}