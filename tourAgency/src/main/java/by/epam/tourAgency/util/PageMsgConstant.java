package by.epam.tourAgency.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PageMsgConstant {
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String HEADER_VALUE = "controller?command=to_inf&msg_key=";
    public static final String HEADER_NAME = "Location";

    public static final String INF_PAGE_PATH = "path.page.inf";
    public static final String BOOKING_PAGE_PATH = "path.page.booking";
    public static final String TOUR_OVERVIEW_PAGE_PATH = "path.page.tourOverview";
    public static final String USER_PROFILE_PAGE_PATH = "path.page.userProfile";
    public static final String WELCOME_PAGE_PATH = "path.page.index";
    public static final String HOME_PAGE_PATH = "path.page.main";
    public static final String LOGIN_PAGE_PATH = "path.page.login";
    public static final String REGISTRATION_PAGE_PATH = "path.page.registration";
    public static final String INF_PAGE_FLAG = "flag.inf_page";
    public static final String TICKET_REGISTRATION_PAGE_PATH = "path.page.ticketRegistration";
    public static final String ABOUT_US_PAGE_PATH = "path.page.aboutUs";
    public static final String TO_BOOKING_PAGE_PATH = "path.page.booking";
    public static final String TO_INF_PAGE_PATH = "path.page.inf";
    public static final String TO_LOGIN_PAGE_PATH = "path.page.login";
    public static final String TO_ORDERS_PAGE_PATH = "path.page.orders";
    public static final String TO_REGISTRATION_PAGE_PATH = "path.page.registration";
    public static final String TO_TICKET_REGISTRATION_PAGE_PATH = "path.page.ticketRegistration";
    public static final String TO_FLIGHTS_PAGE_PATH = "path.page.tickets";
    public static final String TO_TOUR_REGISTRATION_PAGE_PATH = "path.page.tourRegistration";
    public static final String TO_TOURS_PAGE_PATH = "path.page.tours";
    public static final String TO_TOUR_SEARCH_PAGE_PATH = "path.page.tourSearch";
    public static final String MAIN_PAGE_PATH = "path.page.main";

    public static final String TOUR_NAME_EXISTS_ERROR_MSG_KEY = "tourRegistration.message.tourNameExists";
    public static final String OPERATION_SUCCESS_MSG_KEY = "result.message.successful";
    public static final String OPERATION_NOT_SUCCESS_MSG_KEY = "result.message.notSuccessful";
    public static final String REGISTRATION_SUCCESS_MSG_KEY = "registrationResult.message.successful";
    public static final String REGISTRATION_NOT_SUCCESS_MSG_KEY = "registrationResult.message.notSuccessful";
    public static final String DOUBLE_REQ_ERROR_MSG_KEY = "tourRegistration.message.doubleReq";
    public static final String PHONE_EXISTS_MSG_KEY = "registration.message.phoneNumberExists";
    public static final String LOGIN_ERROR_MSG_KEY = "logIn.message.loginError";
    public static final String TOUR_NAME_EXISTS_MSG_KEY = "tourOverview.message.tourNameExists";
    public static final String CHANGE_SURNAME_ERROR_MSG_KEY = "profile.message.errorChangeUserSurname";
    public static final String CHANGE_PN_ERROR_MSG_KEY = "profile.message.errorChangePN";
    public static final String PHONE_NUMBER_EXISTS_MSG_KEY = "registration.message.phoneNumberExists";
    public static final String CHANGE_PASSWORD_ERROR_MSG_KEY = "profile.message.errorChangePassword";
    public static final String CHANGE_PASSWORD_SUCCESS_MSG_KEY = "profile.message.successChangePassword";
    public static final String CHANGE_PASSWORD_NOT_FIND_MSG_KEY = "profile.message.notFindPassword";
    public static final String CHANGE_USER_NAME_ERROR_MSG_KEY = "profile.message.errorChangeUserName";
    public static final String CHANGE_LOGIN_ERROR_MSG_KEY = "profile.message.errorChangeLogin";
    public static final String LOGIN_EXISTS_MSG_KEY = "registration.message.loginExists";
    public static final String CHANGE_EMAIL_ERROR_MSG_KEY = "profile.message.errorChangeEmail";
    public static final String EMAIL_EXISTS_MSG_KEY = "registration.message.emailExists";
    public static final String DATE_ERROR_MSG_KEY = "tourRegistration.message.invalidDate";
    public static final String SUCCESSFUL_MSG_KEY = "bookingResult.message.successful";
    public static final String NOT_SUCCESSFUL_MSG_KEY = "bookingResult.message.notSuccessful";
    public static final String CLIENT_EMAIL_NOT_EXISTS_MSG_KEY = "booking.message.emailNotExists";
    public static final String NULL_PAGE_MSG_KEY = "logIn.message.nullPage";




}
