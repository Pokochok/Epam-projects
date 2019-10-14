package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
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

import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChangeArrivalCountryCommandTest {

    @Mock
    Validation validation;

    @Mock
    UpdateTourLogic updateTourLogic;

    @InjectMocks
    ChangeArrivalCountryCommand changeArrivalCountryCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(changeArrivalCountryCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Not valid params. No attributes after executing")
    void executeFail() throws Exception {
        when(validation.validateTourStringItems(anyString())).thenReturn(false);
        mockMvc.perform(post("/change_arrival_country")
                .param(PARAM_NAME_NEW_ARRIVAL_COUNTRY, "country")
                .param(PARAM_NAME_TOUR_ID, "jo"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)));
    }

    @Test
    @DisplayName("Sucessful updating")
    void executeSuccess() throws Exception {
        when(validation.validateTourStringItems(anyString())).thenReturn(true);
        when(validation.validateId(anyString())).thenReturn(true);
        doNothing().when(updateTourLogic).updateAdultsNumber(anyInt(), anyInt());
        mockMvc.perform(post("/change_arrival_country")
                .param(PARAM_NAME_NEW_ARRIVAL_COUNTRY, "country")
                .param(PARAM_NAME_TOUR_ID, "6"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ARRIVAL_COUNTRY,"country"));
    }
}