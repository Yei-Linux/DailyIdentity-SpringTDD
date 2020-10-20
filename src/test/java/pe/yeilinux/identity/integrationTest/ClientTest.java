package pe.yeilinux.identity.integrationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.factory.FactoriesTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "flyway.enabled=false")
public class ClientTest {
    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void get_clients_of_database() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_client_of_database() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult result = mockMvc.perform(get("/clients/dailyChess"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client_id", is("dailyChess")))
                .andReturn();
    }

    @Test
    public void create_client_on_database() throws Exception {
        String additionalInformationString = new ObjectMapper().writeValueAsString(FactoriesTest.getObjectUtil().parseObjectToMap(Arrays.asList(FactoriesTest.get_additional_information_for_client("phone_number","998675442"))));
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Client client = FactoriesTest.get_client_object("dailyMusic","dailyIdentity,dailyCrypt","Application for manage music client","123",
                        "ms-identity,ms-security","password,refresh_token","","",3600,3600, additionalInformationString, null,1);
        String clientString = new ObjectMapper().writeValueAsString(client);
        MvcResult result = mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON).content(clientString))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void update_client_on_database() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Client client = FactoriesTest.get_client_object("dailyMusic","dailyIdentity,dailyCrypt","Application for manage music client","123",
                "ms-identity,ms-menu,ms-security","password,refresh_token","","",3600,3600,null,null,1);
        String clientString = new ObjectMapper().writeValueAsString(client);
        MvcResult result = mockMvc.perform(put("/clients/dailyMusic")
                .contentType(MediaType.APPLICATION_JSON).content(clientString))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void delete_client_on_database() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult result = mockMvc.perform(delete("/clients/dailyMusic"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
