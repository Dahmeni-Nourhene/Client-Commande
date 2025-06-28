package org.example.ms_client.controllers;

import org.example.ms_client.dto.ClientDTO;
import org.example.ms_client.dto.CommandeDTO;
import org.example.ms_client.entities.Client;
import org.example.ms_client.feign.fallback.ClientCommandeService;
import org.example.ms_client.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;
    private final ClientCommandeService service;

    // ✅ Constructeur pour l'injection des dépendances
    public ClientController(ClientService clientService, ClientCommandeService service) {
        this.clientService = clientService;
        this.service = service;
    }

    // ✅ Convertit Client en ClientDTO
    private ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setAdresse(client.getAdresse());
        dto.setEmail(client.getEmail());
        return dto;
    }

    // ✅ Convertit ClientDTO en Client
    private Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setAdresse(dto.getAdresse());
        client.setEmail(dto.getEmail());
        return client;
    }

    // ✅ Liste de tous les clients
    @GetMapping
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return clients.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ✅ Un client par ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable String id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(value -> ResponseEntity.ok(toDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Ajouter un nouveau client
    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        Client client = toEntity(clientDTO);
        Client created = clientService.createClient(client);
        return toDTO(created);
    }

    // ✅ Modifier un client
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

    // ✅ Supprimer un client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

//    // ✅ Récupérer les commandes d'un client depuis ms-commande
//    @GetMapping("/{id}/commandes")
//    public List<CommandeDTO> getCommandes(@PathVariable String id) {
//        return service.getCommandesByClientId(id);
//    }
@GetMapping("/{id}/commandes")
public ResponseEntity<?> getCommandes(@PathVariable String id) {
    try {
        List<CommandeDTO> commandes = service.getCommandesByClientId(id);

        if(commandes == null || commandes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .body("Service Commande temporairement indisponible");
        }

        return ResponseEntity.ok(commandes);

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Erreur de communication avec le service Commande");
    }
}


}
