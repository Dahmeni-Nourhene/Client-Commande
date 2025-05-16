package org.example.ms_client.mappers;


import org.example.ms_client.dto.ClientDTO;
import org.example.ms_client.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDTO fromEntity(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setAdresse(client.getAdresse()); // ← ici aussi
        dto.setEmail(client.getEmail());
        return dto;
    }

    public Client fromDTO(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setAdresse(dto.getAdresse()); // ← ici aussi
        client.setEmail(dto.getEmail());
        return client;
    }
}
