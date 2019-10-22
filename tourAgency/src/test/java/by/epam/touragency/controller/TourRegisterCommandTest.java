package by.epam.touragency.controller;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.logic.MatchOfUniqueFieldsDetector;
import by.epam.touragency.logic.TourRegistrationLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TourRegisterCommandTest {
    @Mock
    TourRegistrationLogic tourRegistrationLogic;

    @Mock
    MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

    @Mock
    Validation validation;

    @Mock
    MessageManager messageManager;

    @InjectMocks
    TourRegisterCommand tourRegisterCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tourRegisterCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Not valid params. No attributes after executing")
    void executeFail() throws Exception {
        when(tourRegistrationLogic.isValidData(anyString(),  anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString())).thenReturn(false);
        mockMvc.perform(post("/tour_register_command")
                .param(PARAM_NAME_TOUR_NAME, "tourName")
                .param(PARAM_NAME_DEPARTURE_CITY, "departureCity")
                .param(PARAM_NAME_ARRIVAL_CITY, "arrivalCity")
                .param(ATTR_NAME_ARRIVAL_COUNTRY, "arrivalCountry")
                .param(ATTR_NAME_HOTEL, "hotel")
                .param(ATTR_NAME_NUTRITION, "nutrition")
                .param(PARAM_NAME_TOUR_STATUS, "isAvailable")
                .param(ATTR_NAME_CHILDREN_NUMBER, "childrenNumber")
                .param(ATTR_NAME_ADULTS_NUMBER, "adultsNumber")
                .param(ATTR_NAME_PRICE, "price")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDateStr")
                .param(PARAM_NAME_ARRIVAL_DATE, "arrivalDateStr"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH))) ;
    }

    @Test
    @DisplayName("Invalid Date entered")
    void executeInvalidDate() throws Exception {
        when(validation.validateDate(anyString())).thenReturn(new Date().getTime() + 1000, new Date().getTime() + 100);
        when(messageManager.getProperty(anyString(), any(Locale.class))).thenReturn("errorDate");
        when(validation.validateTicketNumbers(anyString())).thenReturn(true);
        when(validation.validateTourStringItems(anyString())).thenReturn(true);
        when(tourRegistrationLogic.isValidData(anyString(),  anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString())).thenReturn(true);
        when(messageManager.getProperty(eq(DATE_ERROR_MSG_KEY), any(Locale.class))).thenReturn("invalidDate");
        mockMvc.perform(post("/tour_register_command")
                .param(PARAM_NAME_TOUR_NAME, "tourName")
                .param(PARAM_NAME_DEPARTURE_CITY, "departureCity")
                .param(PARAM_NAME_ARRIVAL_CITY, "arrivalCity")
                .param(ATTR_NAME_ARRIVAL_COUNTRY, "arrivalCountry")
                .param(ATTR_NAME_HOTEL, "hotel")
                .param(ATTR_NAME_NUTRITION, "nutrition")
                .param(PARAM_NAME_TOUR_STATUS, "isAvailable")
                .param(ATTR_NAME_CHILDREN_NUMBER, "2")
                .param(ATTR_NAME_ADULTS_NUMBER, "2")
                .param(ATTR_NAME_PRICE, "200")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDateStr")
                .param(PARAM_NAME_ARRIVAL_DATE, "arrivalDateStr"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_DATE, "invalidDate"));
    }

    @Test
    @DisplayName("Tour name exists")
    void executeSuccess() throws Exception {
        when(validation.validateDate(anyString())).thenReturn(new Date().getTime() + 1000, new Date().getTime() + 1001);
        when(messageManager.getProperty(anyString(), any(Locale.class))).thenReturn("errorDate");
        when(validation.validateTicketNumbers(anyString())).thenReturn(true);
        when(validation.validateTourStringItems(anyString())).thenReturn(true);
        when(tourRegistrationLogic.isValidData(anyString(),  anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString())).thenReturn(true);
        when(messageManager.getProperty(eq(TOUR_NAME_EXISTS_ERROR_MSG_KEY), any(Locale.class))).thenReturn("tourNameIsExists");
        when(matchOfUniqueFieldsDetector.isExistsTourName(anyString())).thenReturn(true);
        mockMvc.perform(post("/tour_register_command")
                .param(PARAM_NAME_TOUR_NAME, "tourName")
                .param(PARAM_NAME_DEPARTURE_CITY, "departureCity")
                .param(PARAM_NAME_ARRIVAL_CITY, "arrivalCity")
                .param(ATTR_NAME_ARRIVAL_COUNTRY, "arrivalCountry")
                .param(ATTR_NAME_HOTEL, "hotel")
                .param(ATTR_NAME_NUTRITION, "nutrition")
                .param(PARAM_NAME_TOUR_STATUS, "isAvailable")
                .param(ATTR_NAME_CHILDREN_NUMBER, "2")
                .param(ATTR_NAME_ADULTS_NUMBER, "2")
                .param(ATTR_NAME_PRICE, "200")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDateStr")
                .param(PARAM_NAME_ARRIVAL_DATE, "arrivalDateStr"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_TOUR_NAME_EXISTS, "tourNameIsExists"));
    }

    @Test
    @DisplayName("Successful")
    void executeSuccessful() throws Exception {
        when(validation.validateDate(anyString())).thenReturn(new Date().getTime() + 1000, new Date().getTime() + 1001);
        when(messageManager.getProperty(anyString(), any(Locale.class))).thenReturn("errorDate");
        when(validation.validateTicketNumbers(anyString())).thenReturn(true);
        when(validation.validateTourStringItems(anyString())).thenReturn(true);
        when(tourRegistrationLogic.isValidData(anyString(),  anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString())).thenReturn(true);
        doNothing().when(tourRegistrationLogic).addTour(anyString(),  anyLong(), anyLong(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyInt(), anyInt(), any(BigDecimal.class), anyString());
        when(messageManager.getProperty(eq(TOUR_NAME_EXISTS_ERROR_MSG_KEY), any(Locale.class))).thenReturn("tourNameIsExists");
        when(matchOfUniqueFieldsDetector.isExistsTourName(anyString())).thenReturn(false);
        mockMvc.perform(post("/tour_register_command")
                .param(PARAM_NAME_TOUR_NAME, "tourName")
                .param(PARAM_NAME_DEPARTURE_CITY, "departureCity")
                .param(PARAM_NAME_ARRIVAL_CITY, "arrivalCity")
                .param(ATTR_NAME_ARRIVAL_COUNTRY, "arrivalCountry")
                .param(ATTR_NAME_HOTEL, "hotel")
                .param(ATTR_NAME_NUTRITION, "nutrition")
                .param(PARAM_NAME_TOUR_STATUS, "isAvailable")
                .param(ATTR_NAME_CHILDREN_NUMBER, "2")
                .param(ATTR_NAME_ADULTS_NUMBER, "2")
                .param(ATTR_NAME_PRICE, "200")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDateStr")
                .param(PARAM_NAME_ARRIVAL_DATE, "arrivalDateStr"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ConfigurationManager.getProperty(INF_URL_PATH) + "?"
                        + ATTR_NAME_MSG_KEY + "=" + REGISTRATION_SUCCESS_MSG_KEY))
                .andExpect(MockMvcResultMatchers.view().name("redirect:" +
                        ConfigurationManager.getProperty(INF_URL_PATH) + "?"
                        + ATTR_NAME_MSG_KEY + "=" + REGISTRATION_SUCCESS_MSG_KEY));
    }
}