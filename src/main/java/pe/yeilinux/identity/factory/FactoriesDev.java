package pe.yeilinux.identity.factory;

import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.util.ObjectUtil;

public class FactoriesDev {
    public static ObjectUtil getObjectUtil(){
        return new ObjectUtil();
    }

    public static Client get_client_object(String clientId, String resourceIds, String description, String clientSecret, String scope, String authorizedGrantTypes, String webServerRedirectUri, String authorities,
                                           int accessTokenValidity, int refreshTokenValidity, String additionalInformation, String autoApprove, int tokenId){
        Client client = new Client();
        client.setAccess_token_validity(accessTokenValidity);
        client.setDescription(description);
        client.setAdditional_information(additionalInformation);
        client.setAuthorities(authorities);
        client.setAuthorized_grant_types(authorizedGrantTypes);
        client.setAutoapprove(autoApprove);
        client.setClient_id(clientId);
        client.setClient_secret(clientSecret);
        client.setScope(scope);
        client.setResource_ids(resourceIds);
        client.setRefresh_token_validity(refreshTokenValidity);
        client.setWeb_server_redirect_uri(webServerRedirectUri);
        client.setTokenId(tokenId);
        return client;
    }
}
