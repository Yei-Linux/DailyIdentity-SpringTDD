package pe.yeilinux.identity.service.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import pe.yeilinux.identity.config.AdditionalInfoToken;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.factory.FactoriesDev;
import pe.yeilinux.identity.service.ClientService;

import java.util.Arrays;

@Service
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    ClientService clientService;

    @SneakyThrows
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Client client = this.clientService.getClientById(clientId);

        if(client == null){
            throw new ClientRegistrationException("Error");
        }

        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(clientId);
        details.setAuthorizedGrantTypes(Arrays.asList(client.getAuthorized_grant_types().split(",")));
        details.setScope(Arrays.asList(client.getScope().split(",")));
        details.setResourceIds(Arrays.asList(client.getResource_ids().split(",")));
        details.setClientSecret(client.getClient_secret());

        AdditionalInfoToken.clientId = clientId;
        AdditionalInfoToken.additionalInformationClient = (client.getAdditional_information() != null) ?
                                                           FactoriesDev.getObjectUtil().parseStringToMap(client.getAdditional_information()) : null;
        return details;
    }
}