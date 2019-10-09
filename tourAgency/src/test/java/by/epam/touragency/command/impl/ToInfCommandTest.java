package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static by.epam.touragency.util.ParameterConstant.PARAM_NAME_MSG_KEY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ToInfCommandTest {

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Mock
    private ModelAndView modelAndView;

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
    @WithMockUser(username="admin", roles={"CLIENT","ADMIN", "AGENT"})
    void execute() throws Exception {
        Map map = new HashMap<String, Object>();
        map.put(PARAM_NAME_MSG_KEY, "result.message.successful");
        when(modelAndView.getModel()).thenReturn(map);
        mockMvc.perform(post("/to_inf"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/jsp/inf.jsp"));
    }
}