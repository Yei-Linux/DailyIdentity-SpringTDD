package pe.yeilinux.identity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.repository.ClientRepository;
import pe.yeilinux.identity.service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Client> getClients(int tokenId) {
        return this.clientRepository.getClients(tokenId);
    }

    @Override
    public Client getClientById(String client_id) {
        return this.clientRepository.getClientById(client_id);
    }

    @Override
    public void postClient(Client client) {
        client.setClient_secret(this.passwordEncoder.encode(client.getClient_secret()));
        this.clientRepository.postClient(client);
    }

    @Override
    public void udpateClient(Client client, String clientId) {
        this.clientRepository.updateClient(client,clientId);
    }

    @Override
    public void updateClientSecret(Client client, String clientId) {
        //TODO: Implemented encrypt feature in new microservice
        client.setClient_secret(this.passwordEncoder.encode(client.getClient_secret()));
        this.clientRepository.udpateClientSecret(client, clientId);
    }

    @Override
    public void deleteClient(String clientId) {
        this.clientRepository.deleteClient(clientId);
    }
}
