package pe.yeilinux.identity.repository;

import pe.yeilinux.identity.domain.Client;

import java.util.List;

public interface ClientRepository {
    public List<Client> getClients(int tokenId);
    public Client getClientById(String client_id);
    public void postClient(Client client);
    public void updateClient(Client client,String clientId);
    public void udpateClientSecret(Client client,String clientId);
    public void deleteClient(String clientId);
}
