package org.example.ms_client.services;

import org.example.ms_client.dto.CommandeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "ms-commande",
        url = "${commande.service.url:http://localhost:8082}",
        fallback = CommandeRestClientFallback.class
)
public interface CommandeRestClient {

    @GetMapping("/commandes/by-client/{clientId}")
    List<CommandeDTO> getCommandesByClientId(@PathVariable String clientId);
}