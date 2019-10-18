package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Role;
import by.epam.touragency.logic.MatchOfUniqueFieldsDetector;
import by.epam.touragency.logic.OrderChangeLogic;
import by.epam.touragency.logic.TicketRegistrationLogic;
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

import java.util.Date;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TicketRegisterCommandTest {
    @Mock
    TicketRegistrationLogic ticketRegistrationLogic;

    @Mock
    MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

    @Mock
    Validation validation;

    @Mock
    MessageManager messageManager;

    @InjectMocks
    TicketRegisterCommand ticketRegisterCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketRegisterCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Not valid params. No attributes after executing")
    void executeFail() throws Exception {
        when(validation.validateDate(anyString())).thenReturn(new Date().getTime() + 1000, new Date().getTime() + 100);
        when(messageManager.getProperty(anyString(), any(Locale.class))).thenReturn("errorDate");
        mockMvc.perform(post("/ticket_register_command")
                .param(PARAM_NAME_FLIGHT_NUMBER, "flightNumber")
                .param(PARAM_NAME_TICKET_NUMBER, "ticketNumber")
                .param(PARAM_NAME_DEPARTURE_CITY, "departureCity")
                .param(PARAM_NAME_ARRIVAL_CITY, "arrivalCity")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDateStr")
                .param(PARAM_NAME_ARRIVAL_DATE, "arrivalDateStr"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TICKET_REGISTRATION_PAGE_PATH)))
        .andExpect(model().attribute(ATTR_NAME_ERROR_DATE, "errorDate"));
    }

    @Test
    @DisplayName("Successful registration")
    void executeSuccess() throws Exception {
        when(validation.validateDate(anyString())).thenReturn(new Date().getTime() + 1000, new Date().getTime() + 1001);
        when(messageManager.getProperty(anyString(), any(Locale.class))).thenReturn("errorDate");
        when(validation.validateTicketNumbers(anyString())).thenReturn(true);
        when(validation.validateTourStringItems(anyString())).thenReturn(true);
        when(ticketRegistrationLogic.isTicketExists(anyString(), anyString(), anyString(), anyString(), anyLong(),
                anyLong())).thenReturn(false);
        doNothing().when(ticketRegistrationLogic).addTicket(anyString(), anyString(), anyString(), anyString(), anyLong(),
                anyLong());
        mockMvc.perform(post("/ticket_register_command")
                .param(PARAM_NAME_FLIGHT_NUMBER, "flightNumber")
                .param(PARAM_NAME_TICKET_NUMBER, "ticketNumber")
                .param(PARAM_NAME_DEPARTURE_CITY, "departureCity")
                .param(PARAM_NAME_ARRIVAL_CITY, "arrivalCity")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDateStr")
                .param(PARAM_NAME_ARRIVAL_DATE, "arrivalDateStr"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ConfigurationManager.getProperty(INF_URL_PATH) + "?"
                        + ATTR_NAME_MSG_KEY + "=" + REGISTRATION_SUCCESS_MSG_KEY))
                .andExpect(MockMvcResultMatchers.view().name("redirect:" +
                        ConfigurationManager.getProperty(INF_URL_PATH) + "?"
                        + ATTR_NAME_MSG_KEY + "=" + REGISTRATION_SUCCESS_MSG_KEY));
    }

    @Test
    @DisplayName("Not valid data")
    void executeFailValidation() throws Exception {
        when(validation.validateDate(anyString())).thenReturn(new Date().getTime() + 1000, new Date().getTime() + 1001);
        when(messageManager.getProperty(anyString(), any(Locale.class))).thenReturn("errorDate");
        when(validation.validateTicketNumbers(anyString())).thenReturn(false);
        when(matchOfUniqueFieldsDetector.isExistsEmail(anyString())).thenReturn(false);
        when(matchOfUniqueFieldsDetector.isExistsPhoneNumber(anyString())).thenReturn(false);
        doNothing().when(ticketRegistrationLogic).addTicket(anyString(), anyString(), anyString(), anyString(), anyLong(),
                anyLong());
        mockMvc.perform(post("/ticket_register_command")
                .param(PARAM_NAME_FLIGHT_NUMBER, "flightNumber")
                .param(PARAM_NAME_TICKET_NUMBER, "ticketNumber")
                .param(PARAM_NAME_DEPARTURE_CITY, "departureCity")
                .param(PARAM_NAME_ARRIVAL_CITY, "arrivalCity")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDateStr")
                .param(PARAM_NAME_ARRIVAL_DATE, "arrivalDateStr"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ConfigurationManager.getProperty(INF_URL_PATH) + "?"
                        + ATTR_NAME_MSG_KEY + "=" + REGISTRATION_NOT_SUCCESS_MSG_KEY))
                .andExpect(MockMvcResultMatchers.view().name("redirect:" +
                        ConfigurationManager.getProperty(INF_URL_PATH) + "?"
                        + ATTR_NAME_MSG_KEY + "=" + REGISTRATION_NOT_SUCCESS_MSG_KEY));
    }
}