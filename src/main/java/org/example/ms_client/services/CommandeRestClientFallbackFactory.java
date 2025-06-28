package org.example.ms_client.services;

import org.example.ms_client.dto.CommandeDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CommandeRestClientFallbackFactory implements FallbackFactory<CommandeRestClient> {

    @Override
    public CommandeRestClient create(Throwable cause) {
        return new CommandeRestClient() {
            @Override
            public List<CommandeDTO> getCommandesByClientId(String clientId) {
                System.out.println("🛑 FallbackFactory activé : " + cause.getMessage());
                return Collections.emptyList(); // Retour vide = service KO
            }
        };
    }
}
