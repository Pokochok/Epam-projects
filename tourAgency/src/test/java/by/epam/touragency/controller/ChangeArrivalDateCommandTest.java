package by.epam.touragency.controller;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.logic.UpdateTourLogic;
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

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.DATE_ERROR_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChangeArrivalDateCommandTest {
    @Mock
    MessageManager messageManager;

    @Mock
    Validation validation;

    @Mock
    UpdateTourLogic updateTourLogic;

    @InjectMocks
    ChangeArrivalDateCommand changeArrivalDateCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(changeArrivalDateCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Invalid tour id entered. Validation failed")
    void execute() throws Exception {
        when(validation.validateId(anyString())).thenReturn(false);
        mockMvc.perform(post("/change_arrival_date")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDate")
                .param(PARAM_NAME_NEW_ARRIVAL_DATE, "date")
                .param(PARAM_NAME_TOUR_ID, "jo"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)));
    }

    @Test
    @DisplayName("Successful execution")
    void executeSuccess() throws Exception {
        when(validation.validateDate(anyString())).thenReturn(10001L, 10000L);
        when(validation.validateId(anyString())).thenReturn(true);
        when(validation.dateToFormat(any(Long.class))).thenReturn("testDate");
        doNothing().when(updateTourLogic).updateArrivalDate(anyInt(), anyInt());
        mockMvc.perform(post("/change_arrival_date")
                .param(PARAM_NAME_DEPARTURE_DATE, "departureDate")
                .param(PARAM_NAME_NEW_ARRIVAL_DATE, "date")
                .param(PARAM_NAME_TOUR_ID, "6"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ARRIVAL_DATE,"testDate"));
    }

    @Test
    @DisplayName("Returns date error")
    void executeIllegalDate() throws Exception {
        when(validation.validateDate(anyString())).thenReturn(10000L, 10001L);
        when(validation.validateId(anyString())).thenReturn(true);
        when(messageManager.getProperty(eq(DATE_ERROR_MSG_KEY), any(Locale.class))).thenReturn("errorDate");
        doNothing().when(updateTourLogic).updateArrivalDate(anyInt(), anyInt());
        mockMvc.perform(post("/change_arrival_date")
                .param(PARAM_NAME_DEPARTURE_DATE, "date")
                .param(PARAM_NAME_NEW_ARRIVAL_DATE, "date")
                .param(PARAM_NAME_TOUR_ID, "6"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_DATE,"errorDate"));
    }
}