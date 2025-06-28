package org.example.ms_client.feign.fallback;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.ms_client.dto.CommandeDTO;


import org.example.ms_client.services.CommandeRestClient;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class ClientCommandeService implements CommandeRestClient {

    private final CommandeRestClient commandeClient;

    public ClientCommandeService(CommandeRestClient commandeClient) {
        this.commandeClient = commandeClient;
    }

    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackCommandes")
    @Override
    public List<CommandeDTO> getCommandesByClientId(String clientId) {
        return commandeClient.getCommandesByClientId(clientId);
    }


    // 🔁 Méthode exécutée si erreur ou ms_commande est down
    public List<CommandeDTO> fallbackCommandes(String clientId, Throwable t) {
        System.out.println("🚨 Fallback activé car ms_commande est indisponible : " + t.getMessage());
        return Collections.emptyList();
    }
}

