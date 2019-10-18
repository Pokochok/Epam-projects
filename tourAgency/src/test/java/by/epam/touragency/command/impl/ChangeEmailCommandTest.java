package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.logic.UpdateUserLogic;
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

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChangeEmailCommandTest {
    @Mock
    Validation validation;

    @Mock
    UpdateUserLogic updateUserLogic;

    @Mock
    MessageManager messageManager;

    @InjectMocks
    ChangeEmailCommand changeEmailCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(changeEmailCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Invalid parameters entered. Validation failed")
    void execute() throws Exception {
        when(validation.validateEmail(anyString())).thenReturn(false);
        when(updateUserLogic.checkPrincipal()).thenReturn(false);
        mockMvc.perform(post("/change_email")
                .param(PARAM_NAME_NEW_EMAIL, "newEmail"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)));
    }

    @Test
    @DisplayName("Successful execution")
    void executeSuccess() throws Exception {
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(updateUserLogic.checkPrincipal()).thenReturn(false);
        when(updateUserLogic.updateEmail(anyString(), anyString(), anyString())).thenReturn(true);
        mockMvc.perform(post("/change_email")
                .param(PARAM_NAME_NEW_EMAIL, "newEmail"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)));
    }

    @Test
    @DisplayName("Email exists error")
    void executeEmailExists() throws Exception {
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(messageManager.getProperty(eq(EMAIL_EXISTS_MSG_KEY), any(Locale.class))).thenReturn("emailExists");
        when(updateUserLogic.checkPrincipal()).thenReturn(false);
        when(updateUserLogic.updateEmail(any(), anyString(), any())).thenReturn(false);
        mockMvc.perform(post("/change_email")
                .param(PARAM_NAME_NEW_EMAIL, "newEmail"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)));
    }
}