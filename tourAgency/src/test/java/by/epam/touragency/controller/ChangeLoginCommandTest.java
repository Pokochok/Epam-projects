package by.epam.touragency.controller;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.UserPrincipal;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChangeLoginCommandTest {
    @Mock
    UserPrincipal userPrincipal;

    @Mock
    Validation validation;

    @Mock
    UpdateUserLogic updateUserLogic;

    @Mock
    MessageManager messageManager;

    @InjectMocks
    ChangeLoginCommand changeLoginCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(changeLoginCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Invalid login entered. Validation failed")
    void execute() throws Exception {
        when(validation.validateLogin(anyString())).thenReturn(false);
        when(updateUserLogic.checkPrincipal()).thenReturn(true);
        when(updateUserLogic.getUserPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getUserRole()).thenReturn(Role.CLIENT);
        when(userPrincipal.getUserEmail()).thenReturn("email");
        when(messageManager.getProperty(eq(CHANGE_LOGIN_ERROR_MSG_KEY), any())).thenReturn("errorLogin");
        mockMvc.perform(post("/change_login")
                .param(PARAM_NAME_NEW_LOGIN, "newLogin"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_CHANGE_LOGIN,"errorLogin"));
    }

    @Test
    @DisplayName("Successful execution with role AGENT")
    void executeSuccessAgent() throws Exception {
        when(updateUserLogic.checkPrincipal()).thenReturn(true);
        when(updateUserLogic.getUserPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getUserRole()).thenReturn(Role.AGENT);
        when(userPrincipal.getUserEmail()).thenReturn("email");
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(updateUserLogic.updateLogin(anyString(), anyString(), anyString())).thenReturn(true);
        mockMvc.perform(post("/change_login")
                .param(PARAM_NAME_NEW_LOGIN, "newLogin"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)));
    }

    @Test
    @DisplayName("Successful execution with role ADMIN")
    void executeSuccessAdmin() throws Exception {
        when(updateUserLogic.checkPrincipal()).thenReturn(true);
        when(updateUserLogic.getUserPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getUserRole()).thenReturn(Role.ADMIN);
        when(userPrincipal.getUserEmail()).thenReturn("email");
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(updateUserLogic.updateLogin(anyString(), anyString(), anyString())).thenReturn(true);
        mockMvc.perform(post("/change_login")
                .param(PARAM_NAME_NEW_LOGIN, "newLogin"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)));
    }

    @Test
    @DisplayName("Successful execution whth role CLIENT")
    void executeSuccess() throws Exception {
        when(updateUserLogic.checkPrincipal()).thenReturn(true);
        when(updateUserLogic.getUserPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getUserRole()).thenReturn(Role.CLIENT);
        when(userPrincipal.getUserEmail()).thenReturn("email");
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(updateUserLogic.updateLogin(anyString(), anyString(), anyString())).thenReturn(true);
        mockMvc.perform(post("/change_login")
                .param(PARAM_NAME_NEW_LOGIN, "newLogin"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)));
    }

    @Test
    @DisplayName("Login exists error with role CLIENT")
    void executeLoginExistsClietn() throws Exception {
        when(updateUserLogic.checkPrincipal()).thenReturn(true);
        when(updateUserLogic.getUserPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getUserRole()).thenReturn(Role.CLIENT);
        when(userPrincipal.getUserEmail()).thenReturn("email");
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(updateUserLogic.updateLogin(anyString(), anyString(), anyString())).thenReturn(false);
        when(messageManager.getProperty(eq(LOGIN_EXISTS_MSG_KEY), any())).thenReturn("loginExists");
        mockMvc.perform(post("/change_login")
                .param(PARAM_NAME_NEW_LOGIN, "newLogin"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_LOGIN_EXISTS,"loginExists"));
    }

    @Test
    @DisplayName("Login exists error whith role AGENT")
    void executeLoginExistsAgent() throws Exception {
        when(updateUserLogic.checkPrincipal()).thenReturn(true);
        when(updateUserLogic.getUserPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getUserRole()).thenReturn(Role.AGENT);
        when(userPrincipal.getUserEmail()).thenReturn("email");
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(updateUserLogic.updateLogin(anyString(), anyString(), anyString())).thenReturn(false);
        when(messageManager.getProperty(eq(LOGIN_EXISTS_MSG_KEY), any())).thenReturn("loginExists");
        mockMvc.perform(post("/change_login")
                .param(PARAM_NAME_NEW_LOGIN, "newLogin"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_LOGIN_EXISTS,"loginExists"));
    }

    @Test
    @DisplayName("Login exists error with role ADMIN")
    void executeLoginExistsAdmin() throws Exception {
        when(updateUserLogic.checkPrincipal()).thenReturn(true);
        when(updateUserLogic.getUserPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getUserRole()).thenReturn(Role.ADMIN);
        when(userPrincipal.getUserEmail()).thenReturn("email");
        when(validation.validateEmail(anyString())).thenReturn(true);
        when(validation.validateLogin(anyString())).thenReturn(true);
        when(updateUserLogic.updateLogin(anyString(), anyString(), anyString())).thenReturn(false);
        when(messageManager.getProperty(eq(LOGIN_EXISTS_MSG_KEY), any())).thenReturn("loginExists");
        mockMvc.perform(post("/change_login")
                .param(PARAM_NAME_NEW_LOGIN, "newLogin"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_LOGIN_EXISTS,"loginExists"));
    }
}