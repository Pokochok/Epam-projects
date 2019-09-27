package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import org.junit.Assert;
import org.junit.Test;

public class MatchOfUniqueFieldsDetectorTest {

    @Test
    public void testIsExistsEmailExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsEmail("not defined");
        Assert.assertTrue(actual);
    }
    @Test
    public void testIsExistsEmailNotExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsEmail("rauich@rdb.gru");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsPhoneNumberExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsPhoneNumber("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsPhoneNumberNotExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsPhoneNumber("+32212345777");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsLoginExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsLogin("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsLoginNotExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsLogin("garfild");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsTourNameExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsTourName("not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsTourNameNotExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsTourName("not exists");
        Assert.assertFalse(actual);
    }
}