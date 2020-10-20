package pe.yeilinux.identity.service;

import pe.yeilinux.identity.domain.Client;

import java.util.List;

public interface ClientService {
    public List<Client> getClients(int tokenId);
    public Client getClientById(String client_id);
    public void postClient(Client client);
    public void udpateClient(Client client,String clientId);
    public void updateClientSecret(Client client,String clientId);
    public void deleteClient(String clientId);
}
