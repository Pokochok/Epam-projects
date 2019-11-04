package by.epam.touragency.controller;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static by.epam.touragency.util.PageMsgConstant.TO_INF_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.ATTR_NAME_RESULT_INF;
import static by.epam.touragency.util.ParameterConstant.PARAM_NAME_MSG_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ToInfCommandTest {

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Mock
    private Map<String, Object> hashMap;

    @Mock
    ModelAndView modelAndView;

    @Mock
    MessageManager messageManager;

    @InjectMocks
    private ToInfCommand toInfCommand;

    private MockMvc mockMvc;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(toInfCommand).addFilter(springSecurityFilterChain)
                .dispatchOptions(true).build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"CLIENT", "ADMIN", "AGENT"})
    void execute() throws Exception {
        when(messageManager.getProperty(anyString(), any(Locale.class))).thenReturn("testValue");
        mockMvc.perform(post("/to_inf")
                .param(PARAM_NAME_MSG_KEY, "msg"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.view().name(ConfigurationManager.getProperty(TO_INF_PAGE_PATH)))
                .andExpect(model().attribute(ATTR_NAME_RESULT_INF, "testValue"));
    }
}