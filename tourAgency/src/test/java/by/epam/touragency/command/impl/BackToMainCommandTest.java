package by.epam.touragency.command.impl;

import by.epam.touragency.config.WebAppTestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BackToMainCommandTest {

    @InjectMocks
    private BackToMainCommand backToMainCommand;

    private MockMvc mockMvc;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(backToMainCommand).build();
    }


    @Test
    public void execute() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/home"));
        resultActions.andExpect(status().isOk())
                .andExpect(forwardedUrl("/jsp/home.jsp"));
    }
}