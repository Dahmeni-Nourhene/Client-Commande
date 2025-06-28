package org.example.ms_client.services;

import lombok.RequiredArgsConstructor;
import org.example.ms_client.dto.CommandeDTO;
import org.example.ms_client.entities.Client;

import org.example.ms_client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ClientService {



    private  final ClientRepository clientRepository;

    private  final CommandeRestClient commandeRestClient;

    public List<CommandeDTO> getCommandesForClient(String clientId) {
        return commandeRestClient.getCommandesByClientId(clientId);
    }
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(String id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(String id, Client clientDetails) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setNom(clientDetails.getNom());
            client.setEmail(clientDetails.getEmail());
            client.setAdresse(clientDetails.getAdresse());
            return clientRepository.save(client);
        }
        return null;
    }

    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }
}
