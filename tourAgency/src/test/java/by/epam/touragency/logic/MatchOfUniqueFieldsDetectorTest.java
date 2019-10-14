package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MatchOfUniqueFieldsDetectorTest {
    @Autowired
    MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

    @Test
    public void testIsExistsEmailExists() throws LogicException {
        boolean actual = matchOfUniqueFieldsDetector.isExistsEmail("not defined");
        Assert.assertTrue(actual);
    }
    @Test
    public void testIsExistsEmailNotExists() throws LogicException {
        boolean actual = matchOfUniqueFieldsDetector.isExistsEmail("rauich@rdb.gru");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsPhoneNumberExists() throws LogicException {
        boolean actual = matchOfUniqueFieldsDetector.isExistsPhoneNumber("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsPhoneNumberNotExists() throws LogicException {
        boolean actual = matchOfUniqueFieldsDetector.isExistsPhoneNumber("+32212345777");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsLoginExists() throws LogicException {
        boolean actual = matchOfUniqueFieldsDetector.isExistsLogin("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsLoginNotExists() throws LogicException {
        boolean actual = matchOfUniqueFieldsDetector.isExistsLogin("garfild");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsTourNameExists() throws LogicException {
        boolean actual = matchOfUniqueFieldsDetector.isExistsTourName("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsTourNameNotExists() throws LogicException {
        boolean actual = matchOfUniqueFieldsDetector.isExistsTourName("not exists");
        Assert.assertFalse(actual);
    }
}