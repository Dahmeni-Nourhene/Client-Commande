package org.example.ms_client.controllers;

import org.example.ms_client.dto.ClientDTO;
import org.example.ms_client.entities.Client;
import org.example.ms_client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Convertit Client en ClientDTO
    private ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setAdresse(client.getAdresse());
        dto.setEmail(client.getEmail());
        return dto;
    }

    // Convertit ClientDTO en Client
    private Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setAdresse(dto.getAdresse());
        client.setEmail(dto.getEmail());
        return client;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        // Convertir la liste des entités en DTO
        return clients.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable String id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(value -> ResponseEntity.ok(toDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        Client client = toEntity(clientDTO);
        Client created = clientService.createClient(client);
        return toDTO(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable String id, @RequestBody ClientDTO clientDTO) {
        Client client = toEntity(clientDTO);
        Client updated = clientService.updateClient(id, client);
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
