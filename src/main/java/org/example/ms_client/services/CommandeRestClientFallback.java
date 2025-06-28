package org.example.ms_client.services;

import org.example.ms_client.dto.CommandeDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class CommandeRestClientFallback implements CommandeRestClient {

    @Override
    public List<CommandeDTO> getCommandesByClientId(String clientId) {
        System.out.println("🛑 Le service Commande est indisponible ou aucune commande trouvée.");
        return Collections.emptyList(); // Retourne une liste vide au lieu de null
    }
}