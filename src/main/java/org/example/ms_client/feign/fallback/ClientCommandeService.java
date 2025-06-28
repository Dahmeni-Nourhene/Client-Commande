package org.example.ms_client.feign.fallback;

import org.example.ms_client.dto.CommandeDTO;
import org.example.ms_client.services.CommandeRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCommandeService implements CommandeRestClient {

    private final CommandeRestClient commandeClient;

    public ClientCommandeService(CommandeRestClient commandeClient) {
        this.commandeClient = commandeClient;
    }

    @Override
    public List<CommandeDTO> getCommandesByClientId(String clientId) {
        return commandeClient.getCommandesByClientId(clientId);
    }
}
