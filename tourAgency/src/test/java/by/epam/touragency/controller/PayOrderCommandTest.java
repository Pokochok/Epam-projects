package by.epam.touragency.controller;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.logic.OrderChangeLogic;
import by.epam.touragency.logic.ToPageWithListLogic;
import by.epam.touragency.resource.ConfigurationManager;
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
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.TO_ORDERS_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PayOrderCommandTest {
    @Mock
    ToOrdersCommand toOrdersCommand;

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
        when(toOrdersCommand.execute(anyString(), anyString(), anyString(), anyString())).thenReturn(new ModelAndView(ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH)));
        mockMvc.perform(post("/pay_order")
                .param(PARAM_NAME_ORDER_ID, "1")
                .sessionAttr(ATTR_NAME_USER_ROLE, "CLIENT")
                .sessionAttr(ATTR_NAME_USER_ID, "1")
                .param(ATTR_NAME_INDEX, "1")
                .param(ATTR_NAME_CHANGE_PAGE, "1"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH)));
    }

    @Test
    @DisplayName("Successful updating")
    void executeSuccess() throws Exception {
        when(orderChangeLogic.payOrder(anyString())).thenReturn(true);
        when(toOrdersCommand.execute(anyString(), anyString(), anyString(), anyString())).thenReturn(new ModelAndView(ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH)));
        mockMvc.perform(post("/pay_order")
                .param(PARAM_NAME_ORDER_ID, "1")
                .sessionAttr(ATTR_NAME_USER_ROLE, "CLIENT")
                .sessionAttr(ATTR_NAME_USER_ID, "1")
                .param(ATTR_NAME_INDEX, "1")
                .param(ATTR_NAME_CHANGE_PAGE, "1"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH)));
    }
}