package by.epam.touragency.util;


import by.epam.touragency.config.WebAppTestContext;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidationTest {
    @Autowired
    Validation validation;

    @Test
    public void testValidateNameValid() {
        boolean actual = validation.validateName("Alexander");
        Assert.assertTrue(actual);
    }
    @Test
    public void testValidateNameInvalid() {
        boolean actual = validation.validateName("Alexander23*&");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateEmailValid() {
        boolean actual = validation.validateEmail("alexanger1999@gamail.ru");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateEmailInvalid() {
        boolean actual = validation.validateEmail("alexanger1999<$#!gamail.ru");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidatePhoneNumberValid() {
        boolean actual = validation.validatePhoneNumber("+80292114687");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidatePhoneNumberInvalid() {
        boolean actual = validation.validatePhoneNumber("+5sdf55852");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateLoginValid() {
        boolean actual = validation.validateLogin("alexanDER341");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateLoginInvalid() {
        boolean actual = validation.validateLogin("aledfgjfgjfgjfgjfgjfwefsdfwppomkhyuefsdfefsfsdfwefqgxanDER341");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidatePasswordValid() {
        boolean actual = validation.validatePassword("alexanDER341&&^^4");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidatePasswordInvalid() {
        boolean actual = validation.validatePassword("aledfwefsdfwppomkhyuefsdfefsfsdfwefqgxanDER341&&^^4");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateTourStringItemsValid() {
        boolean actual = validation.validateTourStringItems("Best tour");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateTourStringItemsInvalid() {
        boolean actual = validation.validateTourStringItems("Лучший тур!!%;");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateNutritionValid() {
        boolean actual = validation.validateNutrition("RO");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateNutritionInvalid() {
        boolean actual = validation.validateNutrition("rh");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateNumberOfPeopleValid() {
        boolean actual = validation.validateNumberOfPeople("5");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateNumberOfPeopleInvalid() {
        boolean actual = validation.validateNumberOfPeople("5ghd");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidatePriceValid() {
        boolean actual = validation.validatePrice("3219.34");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidatePriceInvalid() {
        boolean actual = validation.validatePrice("3219.3244");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateTicketNumbersValid() {
        boolean actual = validation.validateTicketNumbers("3219");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateTicketNumbersInvalid() {
        boolean actual = validation.validateTicketNumbers("3254dc19");
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateDateValid() {
        long actual = validation.validateDate("2019-08-22");
        long expected = 1566421200000L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testValidateDateInvalid() {
        long actual = validation.validateDate("20190822");
        long expected = -1L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDateToFormatValid() {
        String actual = validation.dateToFormat(1566424800000L);
        String expected = "2019-08-22";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDateToFormatInvalid() {
        String actual = validation.dateToFormat(-1566424800000L);
        String expected = "1970-01-01";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testValidateIdValid() {
        boolean actual = validation.validateTicketNumbers("32544719");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateIdInvalid() {
        boolean actual = validation.validateTicketNumbers("32gj44719");
        Assert.assertFalse(actual);
    }

}