package org.example.ms_client.services;

import org.example.ms_client.dto.CommandeDTO;

import org.example.ms_client.services.CommandeRestClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CommandeRestClientFallback implements CommandeRestClient {

    @Override
    public List<CommandeDTO> getCommandesByClientId(String clientId) {
        System.out.println("🚨 Fallback : ms-commande est indisponible. Retourne liste vide.");
        return Collections.emptyList();
    }
}
