package org.example.ms_client.services;

import org.example.ms_client.dto.CommandeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "ms-commande",
        url = "http://localhost:8082",
        fallback = CommandeRestClientFallback.class // ✅ c’est ça la bonne classe
)
public interface CommandeRestClient {

    @GetMapping("/commandes/by-client/{clientId}")
    List<CommandeDTO> getCommandesByClientId(@PathVariable("clientId") String clientId);
}
