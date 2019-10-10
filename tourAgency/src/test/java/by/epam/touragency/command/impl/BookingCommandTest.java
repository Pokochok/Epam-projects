package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.BookingLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.Validation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingCommandTest {
    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Mock
    BookingLogic bookingLogic;

    @InjectMocks
    private BookingCommand bookingCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingCommand).addFilter(springSecurityFilterChain)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Expected attribute with message client's email not exists")
    @WithMockUser(username = "admin", roles = {"CLIENT", "AGENT"})
    void executeFail() throws Exception {
        mockMvc.perform(post("/booking"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(BOOKING_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(BOOKING_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_EMAIL_NOT_EXISTS,
                        "The client with the entered e-mail does not exist"));
    }

    @Test
    @DisplayName("Expected successful completing of method ang forward to inf page")
    @WithMockUser(username = "admin", roles = {"CLIENT", "AGENT"})
    void executeSuccess() throws Exception {
        when(bookingLogic.addOrder(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(true);
        mockMvc.perform(post("/booking")
                .param(PARAM_NAME_TICKET_ID, "1")
                .param(PARAM_NAME_CLIENT_ID, "1")
                .param(PARAM_NAME_CLIENT_EMAIL, "null")
                .param(PARAM_NAME_AGENT_ID, "1")
                .param(PARAM_NAME_TOUR_ID, "1"))
                .andExpect(status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:" +
                        ConfigurationManager.getProperty(INF_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_RESULT_INF,
                        "Booking was successful"));
    }

    @Test
    @DisplayName("Executing after fail while processing booking adding logic")
    void executeNotSuccess() throws Exception {
        when(bookingLogic.addOrder(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(false);
        mockMvc.perform(post("/booking")
                .param(PARAM_NAME_TICKET_ID, "1")
                .param(PARAM_NAME_CLIENT_ID, "1")
                .param(PARAM_NAME_CLIENT_EMAIL, "null")
                .param(PARAM_NAME_AGENT_ID, "1")
                .param(PARAM_NAME_TOUR_ID, "1"))
                .andExpect(status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:" +
                        ConfigurationManager.getProperty(INF_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_RESULT_INF,
                        "Booking ended with an error. One of the objects no longer exists."));
    }
}