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
import pe.yeilinux.identity.domain.AdditionalInformation;
import pe.yeilinux.identity.factory.FactoriesTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "flyway.enabled=false")
public class AdditionalInfoTest {
    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void get_additional_information_for_token() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult result = mockMvc.perform(get("/additional-information/user-fields")
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        String contentResponse = result.getResponse().getContentAsString();
        AdditionalInformation[] resultAdditionalinfo = new ObjectMapper().readValue(contentResponse,AdditionalInformation[].class);
        List<AdditionalInformation> additionalInformationList = Arrays.asList(resultAdditionalinfo);

        assertEquals(7,additionalInformationList.size());
    }

    @Test
    public void get_additional_information_by_user_for_token() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult result = mockMvc.perform(get("/additional-information?username=jesusalvan2010@gmail.com")
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        String contentResponse = result.getResponse().getContentAsString();
        AdditionalInformation[] resultAdditionalinfo = new ObjectMapper().readValue(contentResponse,AdditionalInformation[].class);
        List<AdditionalInformation> additionalInformationList = Arrays.asList(resultAdditionalinfo);

        assertEquals(7,additionalInformationList.size());
    }

    @Test
    public void create_additional_information_fields_for_token() throws Exception {
        String additionalInformationString = new ObjectMapper().writeValueAsString(Arrays.asList(FactoriesTest.get_additional_information_for_token("id"),
                                                                                                 FactoriesTest.get_additional_information_for_token("first_name")));
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult result = mockMvc.perform(post("/additional-information/token/1")
                .contentType(MediaType.APPLICATION_JSON).content(additionalInformationString)
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isCreated())
                .andReturn();
    }
}
