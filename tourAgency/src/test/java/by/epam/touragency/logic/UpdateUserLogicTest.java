package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UpdateUserLogicTest {
    @Autowired
    private UpdateUserLogic updateUserLogic;

    @Test
    public void testUpdateEmailTrue() throws LogicException {
        boolean actual = updateUserLogic.updateEmail("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        updateUserLogic.updateEmail("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdateEmailFalse() throws LogicException {
        boolean actual = updateUserLogic.updateEmail("AGENT", "not defined", "login");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdatePhoneNumberTrue() throws LogicException {
        boolean actual = updateUserLogic.updatePhoneNumber("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        updateUserLogic.updatePhoneNumber("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdatePhoneNumberFalse() throws LogicException {
        boolean actual = updateUserLogic.updatePhoneNumber("AGENT", "not defined", "login");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateLoginTrue() throws LogicException {
        boolean actual = updateUserLogic.updateLogin("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        updateUserLogic.updateLogin("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdateLoginFalse() throws LogicException {
        boolean actual = updateUserLogic.updateLogin("AGENT", "not defined", "email");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdatePasswordTrue() throws LogicException {
        boolean actual = updateUserLogic.updatePassword("AGENT", "not defined", "1234567890", "1234567890");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdatePasswordFalse() throws LogicException {
        boolean actual = updateUserLogic.updatePassword("AGENT", "not defined", "pass", "not defined");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateNameTrue() throws LogicException {
        boolean actual = updateUserLogic.updateName("AGENT", "not defined", "not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdateNameFalse() throws LogicException {
        boolean actual = updateUserLogic.updateName("AGENT", "login", "not defined");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateSurnameTrue() throws LogicException {
        boolean actual = updateUserLogic.updateSurname("AGENT", "not defined", "not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdateSurnameFalse() throws LogicException {
        boolean actual = updateUserLogic.updateSurname("AGENT", "login", "not defined");
        Assert.assertFalse(actual);
    }
}