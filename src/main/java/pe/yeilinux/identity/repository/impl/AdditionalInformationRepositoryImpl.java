package pe.yeilinux.identity.repository.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.yeilinux.identity.constant.Constant;
import pe.yeilinux.identity.domain.AdditionalInformation;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.domain.GeneralObject;
import pe.yeilinux.identity.mapper.AdditionalInformationMapper;
import pe.yeilinux.identity.mapper.ClientMapper;
import pe.yeilinux.identity.mapper.GeneralMapper;
import pe.yeilinux.identity.repository.AdditionalInformationRepository;
import pe.yeilinux.identity.util.SimpleJdbcCallFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdditionalInformationRepositoryImpl implements AdditionalInformationRepository {

    @Resource(name= Constant.CURRENTJDBCTEMPLATE)
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SimpleJdbcCallFactory simpleJdbcCallFactory;

    @Override
    public List<AdditionalInformation> getAdditionalInformationByUser(String username) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "get_additional_information_by_user",Constant.CURRENTDATABASE)
                .returningResultSet("result",new AdditionalInformationMapper());

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_username",username);

        Map<String,Object> result= simpleJdbcCall.execute(parameters);

        List<AdditionalInformation> additionalInformationList = (List<AdditionalInformation>) result.get("result");

        if( additionalInformationList != null && !additionalInformationList.isEmpty() ){
            return additionalInformationList;
        }
        return null;
    }

    @Override
    public List<AdditionalInformation> getAdditionalInformationFields() {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "get_additional_information_fields",Constant.CURRENTDATABASE)
                .returningResultSet("result",new AdditionalInformationMapper());

        Map<String,Object> result= simpleJdbcCall.execute();

        List<AdditionalInformation> additionalInformationList = (List<AdditionalInformation>) result.get("result");

        if( additionalInformationList != null && !additionalInformationList.isEmpty() ){
            return additionalInformationList;
        }
        return null;
    }

    @Override
    public List<AdditionalInformation> getAdditionalInformation(int tokenId) throws IOException {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "get_additional_information",Constant.CURRENTDATABASE)
                    .returningResultSet("result",new GeneralMapper());
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_token_id",tokenId);

        Map<String,Object> result= simpleJdbcCall.execute(parameters);

        List<GeneralObject> resultToParse = (List<GeneralObject>) result.get("result");
        if( resultToParse != null && !resultToParse.isEmpty() ){
            AdditionalInformation[] additionalInformationList = new ObjectMapper().readValue(resultToParse.get(0).getGeneral(),AdditionalInformation[].class);
            return Arrays.asList(additionalInformationList);
        }
        return null;
    }

    @Override
    public void createAdditionalInformation(String additionalInformation,int tokenId) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "create_additional_information",Constant.CURRENTDATABASE)
                .returningResultSet("result",new AdditionalInformationMapper());
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_additional_information",additionalInformation);
        parameters.put("p_token_id",tokenId);
        simpleJdbcCall.execute(parameters);
    }

    @Override
    public String getValueWithDynamicalField(String field, String username) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "get_value_of_dynamical_field",Constant.CURRENTDATABASE)
                .returningResultSet("result",new GeneralMapper());

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_dynamical_field",field);
        parameters.put("p_username",username);

        Map<String,Object> result= simpleJdbcCall.execute(parameters);

        List<GeneralObject> resultDynamicalField = (List<GeneralObject>) result.get("result");
        if( resultDynamicalField != null && !resultDynamicalField.isEmpty() ){
            return resultDynamicalField.get(0).getGeneral();
        }
        return null;
    }
}
