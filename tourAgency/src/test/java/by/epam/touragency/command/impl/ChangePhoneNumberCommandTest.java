package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
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
class ChangePhoneNumberCommandTest {
    @Mock
    Validation validation;

    @Mock
    UpdateUserLogic updateUserLogic;

    @Mock
    MessageManager messageManager;

    @InjectMocks
    ChangePhoneNumberCommand changePhoneNumberCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(changePhoneNumberCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Invalid parameters entered. Validation failed")
    void execute() throws Exception {
        when(validation.validatePhoneNumber(anyString())).thenReturn(false);
        when(messageManager.getProperty(eq(CHANGE_PN_ERROR_MSG_KEY), any(Locale.class))).thenReturn("invalidPhoneNumber");
        mockMvc.perform(post("/change_phone_number")
                .sessionAttr(PARAM_NAME_USER_LOGIN, "login")
                .param(PARAM_NAME_NEW_PHONE_NUMBER, "newPhoneNumber")
                .sessionAttr(PARAM_NAME_ROLE, "role"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_CHANGE_PN,"invalidPhoneNumber"));
        ;
    }

    @Test
    @DisplayName("Successful execution")
    void executeSuccess() throws Exception {
        when(validation.validatePhoneNumber(anyString())).thenReturn(true);
        when(updateUserLogic.updatePhoneNumber(anyString(), anyString(), anyString())).thenReturn(true);
        mockMvc.perform(post("/change_phone_number")
                .sessionAttr(PARAM_NAME_USER_LOGIN, "login")
                .param(PARAM_NAME_NEW_PHONE_NUMBER, "newPhoneNumber")
                .sessionAttr(PARAM_NAME_ROLE, "role"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_USER_PHONE_NUMBER,"newPhoneNumber"));
    }

    @Test
    @DisplayName("Phone number exists error")
    void executeEmailExists() throws Exception {
        when(validation.validatePhoneNumber(anyString())).thenReturn(true);
        when(updateUserLogic.updatePhoneNumber(anyString(), anyString(), anyString())).thenReturn(false);
        when(messageManager.getProperty(eq(PHONE_NUMBER_EXISTS_MSG_KEY), any(Locale.class))).thenReturn("phoneNumberExists");
        mockMvc.perform(post("/change_phone_number")
                .sessionAttr(PARAM_NAME_USER_LOGIN, "login")
                .param(PARAM_NAME_NEW_PHONE_NUMBER, "newPhoneNumber")
                .sessionAttr(PARAM_NAME_ROLE, "role"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_PN_EXISTS,"phoneNumberExists"));
    }
}