package by.epam.touragency.controller;

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

import java.math.BigDecimal;

import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChangePriceCommandTest {
    @Mock
    Validation validation;

    @Mock
    UpdateTourLogic updateTourLogic;

    @InjectMocks
    ChangePriceCommand changePriceCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(changePriceCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Not valid params. No attributes after executing")
    void executeFail() throws Exception {
        when(validation.validatePrice(anyString())).thenReturn(false);
        mockMvc.perform(post("/change_price")
                .param(PARAM_NAME_NEW_PRICE, "price")
                .param(PARAM_NAME_TOUR_ID, "jo"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)));
    }

    @Test
    @DisplayName("Successful updating")
    void executeSuccess() throws Exception {
        when(validation.validatePrice(anyString())).thenReturn(true);
        when(validation.validateId(anyString())).thenReturn(true);
        doNothing().when(updateTourLogic).updatePrice(any(BigDecimal.class), anyInt());
        mockMvc.perform(post("/change_price")
                .param(PARAM_NAME_NEW_PRICE, "400")
                .param(PARAM_NAME_TOUR_ID, "6"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_PRICE,new BigDecimal(400)));
    }
}