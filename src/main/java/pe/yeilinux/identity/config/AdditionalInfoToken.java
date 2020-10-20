package pe.yeilinux.identity.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.domain.User;
import pe.yeilinux.identity.domain.UserDetails;
import pe.yeilinux.identity.service.AdditionalInformationService;
import pe.yeilinux.identity.service.ClientService;
import pe.yeilinux.identity.service.UserService;
import pe.yeilinux.identity.util.ObjectUtil;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdditionalInfoToken implements TokenEnhancer {

    @Autowired
    public UserService userService;
    @Autowired
    public ClientService clientService;
    @Autowired
    private ObjectUtil objectUtil;

    @Autowired
    private AdditionalInformationService additionalInformationService;

    public static Map<String,Object> additionalInformationClient;
    public static String clientId;

    @SneakyThrows
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Client client = this.clientService.getClientById(clientId);
        User user = this.userService.findByUserName(authentication.getName());
        UserDetails userDetails = this.userService.getUserDetails(user.getUserName());
        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo = this.objectUtil.setAdditionalInformationUser(this.additionalInformationService.getAdditionalInformation(client.getTokenId()),user.getUserName());
        additionalInfo.put("additional_information_client",additionalInformationClient);
        additionalInfo.put("additional_information_user",userDetails);
        additionalInfo.put("email",authentication.getName());
        additionalInfo.put("phone",user.getPhoneNumber());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
        return jwtAccessTokenConverter.enhance(accessToken, authentication);
    }
}
