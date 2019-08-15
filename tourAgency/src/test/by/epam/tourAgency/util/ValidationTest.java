package by.epam.tourAgency.util;

import by.epam.tourAgency.util.Validation;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ValidationTest {
    @Test
    public void testValidateNameValid() {
        boolean actual = Validation.validateName("Alexander");
        Assert.assertTrue(actual);
    }
    @Test
    public void testValidateNameInvalid() {
        boolean actual = Validation.validateName("Alexander23*&");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateEmailValid() {
        boolean actual = Validation.validateEmail("alexanger1999@gamail.ru");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateEmailInvalid() {
        boolean actual = Validation.validateEmail("alexanger1999<$#!gamail.ru");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidatePhoneNumberValid() {
        boolean actual = Validation.validatePhoneNumber("+80292114687");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidatePhoneNumberInvalid() {
        boolean actual = Validation.validatePhoneNumber("+5sdf55852");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateLoginValid() {
        boolean actual = Validation.validateLogin("alexanDER341");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateLoginInvalid() {
        boolean actual = Validation.validateLogin("aledfgjfgjfgjfgjfgjfwefsdfwppomkhyuefsdfefsfsdfwefqgxanDER341");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidatePasswordValid() {
        boolean actual = Validation.validatePassword("alexanDER341&&^^4");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidatePasswordInvalid() {
        boolean actual = Validation.validatePassword("aledfwefsdfwppomkhyuefsdfefsfsdfwefqgxanDER341&&^^4");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateTourStringItemsValid() {
        boolean actual = Validation.validateTourStringItems("Best tour");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateTourStringItemsInvalid() {
        boolean actual = Validation.validateTourStringItems("Лучший тур!!%;");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateNutritionValid() {
        boolean actual = Validation.validateNutrition("RO");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateNutritionInvalid() {
        boolean actual = Validation.validateNutrition("rh");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateNumberOfPeopleValid() {
        boolean actual = Validation.validateNumberOfPeople("5");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateNumberOfPeopleInvalid() {
        boolean actual = Validation.validateNumberOfPeople("5ghd");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidatePriceValid() {
        boolean actual = Validation.validatePrice("3219.34");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidatePriceInvalid() {
        boolean actual = Validation.validatePrice("3219.3244");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateTicketNumbersValid() {
        boolean actual = Validation.validateTicketNumbers("3219");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateTicketNumbersInvalid() {
        boolean actual = Validation.validateTicketNumbers("3254dc19");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateDateValid() {
        long actual = Validation.validateDate("2019-08-22");
        long expected = 1566424800000L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testValidateDateInvalid() {
        long actual = Validation.validateDate("20190822");
        long expected = -1L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDateToFormatValid() {
        String actual = Validation.dateToFormat(1566424800000L);
        String expected = "2019-08-22";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDateToFormatInvalid() {
        String actual = Validation.dateToFormat(-1566424800000L);
        String expected = "1970-01-01";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testValidateIdValid() {
        boolean actual = Validation.validateTicketNumbers("32544719");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateIdInvalid() {
        boolean actual = Validation.validateTicketNumbers("32gj44719");
        Assert.assertFalse(actual);
    }

}