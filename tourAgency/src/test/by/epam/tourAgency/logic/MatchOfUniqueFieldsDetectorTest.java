package by.epam.tourAgency.logic;

import by.epam.tourAgency.exception.LogicException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MatchOfUniqueFieldsDetectorTest {

    @Test
    public void testIsExistsEmailExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsEmail("fomich@mail.ru");
        Assert.assertTrue(actual);
    }
    @Test
    public void testIsExistsEmailNotExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsEmail("rauich@rdb.gru");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsPhoneNumberExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsPhoneNumber("+30012345666");
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsExistsPhoneNumberNotExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsPhoneNumber("+32212345777");
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsExistsLoginExists() throws LogicException {
        boolean actual = MatchOfUniqueFieldsDetector.isExistsLogin("ppavlushka");
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