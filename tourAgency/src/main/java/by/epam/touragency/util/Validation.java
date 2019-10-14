package by.epam.touragency.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class contains validation logic
 */
@Service
public class Validation {
    private static final String NAME_SURNAME_FORMAT = "^[^!@#$%^&*().,_\\d=|?`~/<>']{1,30}$";
    private static final String TOUR_STRING_ITEMS_FORMAT = "^[\\sa-zA-Z.,_%+-]{1,40}$";
    private static final String EMAIL_FORMAT = "^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    private static final String PHONE_NUMBER_FORMAT = "^[+]\\d{10,12}$";
    private static final String NUTRITION_FORMAT = "^[A-Z]{2,3}[+]?$";
    private static final String PEOPLE_NUMBER_FORMAT = "^([0-4]?\\d|50)$";
    private static final String PRICE_FORMAT = "^([1]?\\d?\\d?\\d?\\d?\\d([.]\\d\\d)?|200000([.]\\d\\d)?)$";
    private static final String TICKET_NUMBERS_FORMAT = "^([1]?\\d{1,9}|2000000000)$";
    private static final String ID_FORMAT = "^([1]?\\d{1,9}|2000000000)$";

    /**
     * Validates name
     * @param name name
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validateName(String name){
        return name != null && name.matches(NAME_SURNAME_FORMAT);
    }

    /**
     * Validates email
     * @param email email
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validateEmail(String email){
        return email != null && email.length() >= 6 && email.length() <= 100 && email.matches(EMAIL_FORMAT);
    }

    /**
     * Validates phone number
     * @param phoneNumber phone number
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validatePhoneNumber(String phoneNumber){
        return phoneNumber != null && phoneNumber.matches(PHONE_NUMBER_FORMAT);
    }

    /**
     * Validates login
     * @param login login
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validateLogin(String login){
        return login != null && login.length() >= 4 && login.length() <= 50;
    }

    /**
     * Validates password
     * @param password password
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validatePassword(String password){
        return password != null && password.length() >= 6 && password.length() <= 50;
    }

    /**
     * Validates strings with names of countries, cities, hotels, etc
     * @param tourName tour name
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validateTourStringItems(String tourName){
        return tourName != null && tourName.matches(TOUR_STRING_ITEMS_FORMAT);
    }

    /**
     * Validates nutrition
     * @param nutrition nutrition
     * @return true, if validation completed successfully, and false - if not
     */
    public boolean validateNutrition(String nutrition){
        return nutrition != null && nutrition.matches(NUTRITION_FORMAT);
    }

    /**
     * Validates strings, which contains number of children or adults
     * @param peopleNumber people number
     * @return true, if validation completed successfully, and false - if not
     */
    public boolean validateNumberOfPeople(String peopleNumber){
        return peopleNumber != null && peopleNumber.matches(PEOPLE_NUMBER_FORMAT);
    }

    /**
     * Validates price
     * @param price price
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validatePrice(String price){
        return price != null && price.matches(PRICE_FORMAT);
    }

    /**
     * Validates strings, which contains ticket or flight number
     * @param number number
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validateTicketNumbers(String number){
        return number != null && number.matches(TICKET_NUMBERS_FORMAT);
    }

    /**
     * Validates date and if it valid, return valid long value
     * @param date date
     * @return -1 if date is null or invalid, and returns related long value
     * if date is valid
     */
    public  long validateDate(String date){
        if (date == null){
            return -1;
        }
        long dateLong = -1;
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateLong = formatForDateNow.parse(date).getTime();
        } catch (ParseException ignored) {
        }
        return dateLong;
    }

    /**
     * Validates long value and returns date
     * @param dateLong long value related to date
     * @return string representation of related date if it valid, and returns default date - if not
     */
    public  String dateToFormat(long dateLong){
        if (dateLong < 0){
            return "1970-01-01";
        }
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        return formatForDateNow.format(dateLong);
    }

    /**
     * Validates ID
     * @param id ID
     * @return true, if validation completed successfully, and false - if not
     */
    public  boolean validateId(String id){
        return id != null && id.matches(ID_FORMAT);
    }
}
