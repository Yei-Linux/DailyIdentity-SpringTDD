package pe.yeilinux.identity.integrationTest;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pe.yeilinux.identity.domain.Token;
import pe.yeilinux.identity.factory.FactoriesTest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "flyway.enabled=false")
public class SignatureTest {
    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void get_signature_of_token() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult result = mockMvc.perform(get("/signature"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.signingKey", is("123")))
                .andExpect(jsonPath("$.verifierKey", is("123")))
                .andReturn();
    }

    @Test
    public void update_signature_of_token() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Token token = FactoriesTest.get_token("","","",0,"","","1234","1234");
        String tokenString = new ObjectMapper().writeValueAsString(token);

        MvcResult result = mockMvc.perform(put("/signature")
                .contentType(MediaType.APPLICATION_JSON).content(tokenString))
                .andExpect(status().isOk())
                .andReturn();
    }
}
