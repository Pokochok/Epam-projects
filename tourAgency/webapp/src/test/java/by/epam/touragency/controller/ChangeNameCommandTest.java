package by.epam.touragency.controller;

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

import static by.epam.touragency.util.PageMsgConstant.CHANGE_USER_NAME_ERROR_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.USER_PROFILE_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChangeNameCommandTest {
    @Mock
    Validation validation;

    @Mock
    UpdateUserLogic updateUserLogic;

    @Mock
    MessageManager messageManager;

    @InjectMocks
    ChangeNameCommand changeNameCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(changeNameCommand)
                .dispatchOptions(true).build();
    }

    @Test
    @DisplayName("Invalid name entered. Validation failed")
    void execute() throws Exception {
        when(updateUserLogic.checkPrincipal()).thenReturn(false);
        when(validation.validateName(anyString())).thenReturn(false);
        when(messageManager.getProperty(eq(CHANGE_USER_NAME_ERROR_MSG_KEY), any(Locale.class))).thenReturn("invalidName");
        mockMvc.perform(post("/change_user_name")
                .sessionAttr(PARAM_NAME_USER_LOGIN, "login")
                .param(PARAM_NAME_NEW_NAME, "newName")
                .sessionAttr(PARAM_NAME_ROLE, "role"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_ERROR_CHANGE_USER_NAME,"invalidName"));
    }

    @Test
    @DisplayName("Successful execution")
    void executeSuccess() throws Exception {
        when(updateUserLogic.checkPrincipal()).thenReturn(false);
        when(validation.validateName(anyString())).thenReturn(true);
        when(updateUserLogic.updateName(anyString(), anyString(), anyString())).thenReturn(true);
        mockMvc.perform(post("/change_user_name")
                .sessionAttr(PARAM_NAME_USER_LOGIN, "login")
                .param(PARAM_NAME_NEW_NAME, "newName")
                .sessionAttr(PARAM_NAME_ROLE, "role"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_USER_NAME,"newName"));
    }
}