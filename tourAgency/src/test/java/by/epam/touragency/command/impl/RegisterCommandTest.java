package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Role;
import by.epam.touragency.logic.MatchOfUniqueFieldsDetector;
import by.epam.touragency.logic.OrderChangeLogic;
import by.epam.touragency.logic.UserRegistrationLogic;
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
class RegisterCommandTest {
    @Mock
    UserRegistrationLogic userRegistrationLogic;

    @Mock
    MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

    @Mock
    Validation validation;

    @Mock
    OrderChangeLogic orderChangeLogic;

    @Mock
    MessageManager messageManager;

    @InjectMocks
    RegisterCommand registerCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registerCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Not valid params. No attributes after executing")
    void executeFail() throws Exception {
        when(validation.validateName(anyString())).thenReturn(false);
        mockMvc.perform(post("/registration")
                .param(PARAM_NAME_NAME, "name")
                .param(PARAM_NAME_SURNAME, "surname")
                .param(PARAM_NAME_EMAIL, "email")
                .param(PARAM_NAME_PHONE_NUMBER, "phoneNumber")
                .param(PARAM_NAME_LOGIN, "login")
                .param(PARAM_NAME_PASSWORD, "password")
                .param(PARAM_NAME_USER_ROLE, "role"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH)));
    }

    @Test
    @DisplayName("Successful registration")
    void executeSuccess() throws Exception {
        when(validation.validateName(anyString())).thenReturn(true);
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validatePhoneNumber(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(validation.validatePassword(anyString())).thenReturn(true);
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(false);
        when(matchOfUniqueFieldsDetector.isExistsEmail(anyString())).thenReturn(false);
        when(matchOfUniqueFieldsDetector.isExistsPhoneNumber(anyString())).thenReturn(false);
        doNothing().when(userRegistrationLogic).addUser(anyString(), anyString(), anyString(), anyString(), anyString(),
                anyString(), any(Role.class));
        mockMvc.perform(post("/registration")
                .param(PARAM_NAME_NAME, "name")
                .param(PARAM_NAME_SURNAME, "surname")
                .param(PARAM_NAME_EMAIL, "email")
                .param(PARAM_NAME_PHONE_NUMBER, "phoneNumber")
                .param(PARAM_NAME_LOGIN, "login")
                .param(PARAM_NAME_PASSWORD, "password")
                .param(PARAM_NAME_USER_ROLE, "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(TO_INF_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TO_INF_PAGE_PATH)))
        .andExpect(model().attribute(ATTR_NAME_MSG_KEY, REGISTRATION_SUCCESS_MSG_KEY));
    }

    @Test
    @DisplayName("Fields exist. Return common messages")
    void executeExistsFields() throws Exception {
        when(validation.validateName(anyString())).thenReturn(true);
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validatePhoneNumber(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(validation.validatePassword(anyString())).thenReturn(true);
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(true);
        when(matchOfUniqueFieldsDetector.isExistsEmail(anyString())).thenReturn(true);
        when(matchOfUniqueFieldsDetector.isExistsPhoneNumber(anyString())).thenReturn(true);
        when(messageManager.getProperty(eq(LOGIN_EXISTS_MSG_KEY), any(Locale.class))).thenReturn("loginExists");
        when(messageManager.getProperty(eq(EMAIL_EXISTS_MSG_KEY), any(Locale.class))).thenReturn("emailExists");
        when(messageManager.getProperty(eq(PHONE_EXISTS_MSG_KEY), any(Locale.class))).thenReturn("phoneNumberExists");
        mockMvc.perform(post("/registration")
                .param(PARAM_NAME_NAME, "name")
                .param(PARAM_NAME_SURNAME, "surname")
                .param(PARAM_NAME_EMAIL, "email")
                .param(PARAM_NAME_PHONE_NUMBER, "phoneNumber")
                .param(PARAM_NAME_LOGIN, "login")
                .param(PARAM_NAME_PASSWORD, "password")
                .param(PARAM_NAME_USER_ROLE, "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_LOGIN_EXISTS, "loginExists"))
                .andExpect(model().attribute(ATTR_NAME_ERROR_EMAIL_EXISTS, "emailExists"))
                .andExpect(model().attribute(ATTR_NAME_ERROR_PHONE, "phoneNumberExists"));
    }
}