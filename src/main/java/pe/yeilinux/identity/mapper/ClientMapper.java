package pe.yeilinux.identity.mapper;

import org.springframework.jdbc.core.RowMapper;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Client client = new Client();
        client.setDescription(resultSet.getString("description"));
        client.setAccess_token_validity(resultSet.getInt("access_token_validity"));
        client.setAdditional_information(resultSet.getString("additional_information"));
        client.setAuthorities(resultSet.getString("authorities"));
        client.setAuthorized_grant_types(resultSet.getString("authorized_grant_types"));
        client.setAutoapprove(resultSet.getString("autoapprove"));
        client.setClient_id(resultSet.getString("client_id"));
        client.setClient_secret(resultSet.getString("client_secret"));
        client.setScope(resultSet.getString("scope"));
        client.setResource_ids(resultSet.getString("resource_ids"));
        client.setRefresh_token_validity(resultSet.getInt("refresh_token_validity"));
        client.setWeb_server_redirect_uri(resultSet.getString("web_server_redirect_uri"));
        client.setTokenId(resultSet.getInt("token_id"));
        return  client;
    }
}