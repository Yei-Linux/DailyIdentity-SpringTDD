package pe.yeilinux.identity.factory;

import org.springframework.util.LinkedMultiValueMap;
import pe.yeilinux.identity.config.AdditionalInfoToken;
import pe.yeilinux.identity.domain.*;
import pe.yeilinux.identity.helper.Helpers;
import pe.yeilinux.identity.util.ObjectUtil;

import java.util.Arrays;
import java.util.List;

public class FactoriesTest {
    public static User get_user_object(String userName,String password){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }

    public static Token get_token(String access_token,String token_type,String refresh_token,int expires_in,String scope,String jti,String signingKey,String verifierKey){
        Token token = new Token();
        token.setVerifierKey(verifierKey);
        token.setSigningKey(signingKey);
        token.setAccess_token(access_token);
        token.setExpires_in(expires_in);
        token.setJti(jti);
        token.setRefresh_token(refresh_token);
        token.setScope(scope);
        token.setToken_type(token_type);
        return token;
    }

    public static Client get_client_object(String clientId,String resourceIds,String description,String clientSecret,String scope,String authorizedGrantTypes,String webServerRedirectUri,String authorities,
                                           int accessTokenValidity,int refreshTokenValidity,String additionalInformation,String autoApprove,int tokenId){
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

    public static AdditionalInformation get_additional_information_for_client(String field,String value){
        AdditionalInformation additionalInformation = new AdditionalInformation();
        additionalInformation.setField(field);
        additionalInformation.setValue(value);
        return additionalInformation;
    }

    public static AdditionalInformation get_additional_information_for_token(String field){
        AdditionalInformation additionalInformation = new AdditionalInformation();
        additionalInformation.setField(field);
        return additionalInformation;
    }

    public static List<Application> get_clients_application_array(){
        return Arrays.asList(new Application("dailyChess","123","ms-identity ms-security")
                            ,new Application("dailyCode","12345","ms-identity ms-security"));
    }

    public static Helpers getHelper(){
        return new Helpers();
    }

    public static ObjectUtil getObjectUtil() {
        return new ObjectUtil();
    }

    public static LinkedMultiValueMap<String, String> getParamsToLogin(){
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("username", "jesusalvan2010@gmail.com");
        requestParams.add("password", "123");
        requestParams.add("grant_type","password");
        return requestParams;
    }
}
