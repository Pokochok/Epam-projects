package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.logic.OrderChangeLogic;
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
import static by.epam.touragency.util.PageMsgConstant.TO_ORDERS_PAGE_PATH;
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
class PayOrderCommandTest {
    @Mock
    OrderChangeLogic orderChangeLogic;

    @InjectMocks
    PayOrderCommand payOrderCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(payOrderCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Not valid params. No attributes after executing")
    void executeFail() throws Exception {
        when(orderChangeLogic.payOrder(anyString())).thenReturn(false);
        mockMvc.perform(post("/pay_order")
                .sessionAttr(ATTR_NAME_USER_ROLE, "ADMIN")
                .sessionAttr(ATTR_NAME_USER_ID, "userId"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH)));
    }

    @Test
    @DisplayName("Successful updating")
    void executeSuccess() throws Exception {
        when(orderChangeLogic.payOrder(anyString())).thenReturn(true);
        mockMvc.perform(post("/pay_order")
                .sessionAttr(ATTR_NAME_USER_ROLE, "ADMIN")
                .sessionAttr(ATTR_NAME_USER_ID, "userId"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH)));
    }
}