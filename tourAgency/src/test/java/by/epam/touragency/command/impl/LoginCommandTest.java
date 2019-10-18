package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.User;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.logic.LoginLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.apache.commons.httpclient.Credentials;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginCommandTest {
    @Mock
    MessageManager messageManager;

    @InjectMocks
    LoginCommand loginCommand;

    private MockMvc mockMvc;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginCommand).build();
    }

    @Test
    @DisplayName("Success execution")
    void execute() throws Exception {
        when(messageManager.getProperty(eq(LOGIN_ERROR_MSG_KEY), any(Locale.class))).thenReturn("incorrectCredentials");
        mockMvc.perform(get("/fail_login"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(ConfigurationManager.getProperty(HOME_PAGE_PATH)))
        .andExpect(model().attribute(ATTR_NAME_ERROR_LOGIN, "incorrectCredentials"));
    }
}