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

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "flyway.enabled=false")
public class GetTokenTest {

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void get_token_on_correct_login() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .params(FactoriesTest.getParamsToLogin())
                .with(httpBasic("dailyChess","123"))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    public void not_get_token_on_incorrect_login() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .params(FactoriesTest.getParamsToLogin())
                .with(httpBasic("dailyChess","1234"))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void get_token_on_more_of_than_one_application() throws Exception {
        int randomIndex = (int) FactoriesTest.getHelper().getRandomIndexArray();
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .params(FactoriesTest.getParamsToLogin())
                .with(httpBasic(FactoriesTest.get_clients_application_array().get(randomIndex).getClientId(), FactoriesTest.get_clients_application_array().get(randomIndex).getClientSecret()))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    public void verify_scopes_on_get_token_on_more_of_than_application() throws Exception {
        int randomIndex = (int) FactoriesTest.getHelper().getRandomIndexArray();
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        MvcResult result = mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .params(FactoriesTest.getParamsToLogin())
                .with(httpBasic(FactoriesTest.get_clients_application_array().get(randomIndex).getClientId(), FactoriesTest.get_clients_application_array().get(randomIndex).getClientSecret()))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scope", is(FactoriesTest.get_clients_application_array().get(randomIndex).getScopes())))
                .andReturn();
    }

    @Test
    public void verify_exist_additional_info_user_at_get_token() throws Exception {
        int randomIndex = (int) FactoriesTest.getHelper().getRandomIndexArray();
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        MvcResult result = mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .params(FactoriesTest.getParamsToLogin())
                .with(httpBasic(FactoriesTest.get_clients_application_array().get(randomIndex).getClientId(), FactoriesTest.get_clients_application_array().get(randomIndex).getClientSecret()))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        String contentResponse = result.getResponse().getContentAsString();
        Token token = new ObjectMapper().readValue(contentResponse,Token.class);
        List<String> jwtParts = FactoriesTest.getHelper().decodeJWT(token.getAccess_token());
        List<String> jwtBodyKeys = FactoriesTest.getHelper().getKeysOfObject(jwtParts.get(1));
        List<String> keyFound = jwtBodyKeys.stream().filter(key -> key.equals("phone_number")).collect(Collectors.toList());

        assertEquals(1,keyFound.size());
    }

    // TODO: Need to complete test
    @Test
    public void verify_exist_signature_at_get_token() throws Exception {
        int randomIndex = (int) FactoriesTest.getHelper().getRandomIndexArray();
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        MvcResult result = mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .params(FactoriesTest.getParamsToLogin())
                .with(httpBasic(FactoriesTest.get_clients_application_array().get(randomIndex).getClientId(), FactoriesTest.get_clients_application_array().get(randomIndex).getClientSecret()))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
        String contentResponse = result.getResponse().getContentAsString();
        Token token = new ObjectMapper().readValue(contentResponse,Token.class);
        List<String> jwtParts = FactoriesTest.getHelper().decodeJWT(token.getAccess_token());
        System.out.println(jwtParts.get(2));
    }
}
