package pe.yeilinux.identity.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.yeilinux.identity.constant.Constant;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.mapper.ClientMapper;
import pe.yeilinux.identity.repository.ClientRepository;
import pe.yeilinux.identity.util.SimpleJdbcCallFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Resource(name= Constant.CURRENTJDBCTEMPLATE)
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SimpleJdbcCallFactory simpleJdbcCallFactory;

    @Override
    public List<Client> getClients(int tokenId) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "find_clients_all",Constant.CURRENTDATABASE)
                .returningResultSet("result",new ClientMapper());

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_token_id",tokenId);

        Map<String,Object> result= simpleJdbcCall.execute(parameters);
        List<Client> clients = (List<Client>) result.get("result");

        if( clients != null && !clients.isEmpty() ){
            return clients;
        }
        return null;
    }

    @Override
    public Client getClientById(String client_id) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "find_client_by_id",Constant.CURRENTDATABASE)
                .returningResultSet("result",new ClientMapper());

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_client_id",client_id);

        Map<String,Object> result= simpleJdbcCall.execute(parameters);
        List<Client> clients = (List<Client>) result.get("result");

        if( clients != null && !clients.isEmpty() ){
            return clients.get(0);
        }
        return null;
    }

    @Override
    public void postClient(Client client) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "insert_client",Constant.CURRENTDATABASE);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_access_token_validity",client.getAccess_token_validity());
        parameters.put("p_additional_information",client.getAdditional_information());
        parameters.put("p_description",client.getDescription());
        parameters.put("p_authorities",client.getAuthorities());
        parameters.put("p_authorized_grant_types",client.getAuthorized_grant_types());
        parameters.put("p_autoapprove",client.getAutoapprove());
        parameters.put("p_client_id",client.getClient_id());
        parameters.put("p_client_secret",client.getClient_secret());
        parameters.put("p_refresh_token_validity",client.getRefresh_token_validity());
        parameters.put("p_resource_ids",client.getResource_ids());
        parameters.put("p_scope",client.getScope());
        parameters.put("p_web_server_redirect_uri",client.getWeb_server_redirect_uri());
        parameters.put("p_token_id",client.getTokenId());

        simpleJdbcCall.execute(parameters);
    }

    @Override
    public void updateClient(Client client, String clientId) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "update_client",Constant.CURRENTDATABASE);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_client_id_where",clientId);

        parameters.put("p_access_token_validity",client.getAccess_token_validity());
        parameters.put("p_description",client.getDescription());
        parameters.put("p_additional_information",client.getAdditional_information());
        parameters.put("p_authorities",client.getAuthorities());
        parameters.put("p_authorized_grant_types",client.getAuthorized_grant_types());
        parameters.put("p_autoapprove",client.getAutoapprove());
        parameters.put("p_client_id",client.getClient_id());
        parameters.put("p_refresh_token_validity",client.getRefresh_token_validity());
        parameters.put("p_resource_ids",client.getResource_ids());
        parameters.put("p_scope",client.getScope());
        parameters.put("p_web_server_redirect_uri",client.getWeb_server_redirect_uri());

        simpleJdbcCall.execute(parameters);
    }

    @Override
    public void udpateClientSecret(Client client, String clientId) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "update_client_secret",Constant.CURRENTDATABASE);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_client_id_where",clientId);

        parameters.put("p_client_secret",client.getClient_secret());

        simpleJdbcCall.execute(parameters);
    }

    @Override
    public void deleteClient(String clientId) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "delete_client_by_id",Constant.CURRENTDATABASE);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_client_id",clientId);

        simpleJdbcCall.execute(parameters);
    }
}
