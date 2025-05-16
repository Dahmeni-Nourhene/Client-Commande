package org.example.ms_commande.services;

import org.example.ms_commande.dto.CommandeDTO;
import org.example.ms_commande.entities.Commande;
import org.example.ms_commande.exceptions.CommandeNotFoundException;
import org.example.ms_commande.mappers.CommandeMapper;
import org.example.ms_commande.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<CommandeDTO> getAllCommandes() {
        return commandeRepository.findAll()
                .stream()
                .map(CommandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CommandeDTO getCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new CommandeNotFoundException("Commande not found with id " + id));
        return CommandeMapper.toDTO(commande);
    }

    public CommandeDTO createCommande(CommandeDTO commandeDTO) {
        Commande commande = CommandeMapper.toEntity(commandeDTO);
        Commande saved = commandeRepository.save(commande);
        return CommandeMapper.toDTO(saved);
    }

    public CommandeDTO updateCommande(Long id, CommandeDTO commandeDTO) {
        Commande updated = commandeRepository.findById(id)
                .map(commande -> {
                    commande.setReference(commandeDTO.getReference());
                    commande.setClientId(commandeDTO.getClientId());
                    commande.setMontant(commandeDTO.getMontant());
                    return commandeRepository.save(commande);
                })
                .orElseThrow(() -> new CommandeNotFoundException("Commande not found with id " + id));

        return CommandeMapper.toDTO(updated);
    }

    public void deleteCommande(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new CommandeNotFoundException("Commande not found with id " + id);
        }
        commandeRepository.deleteById(id);
    }
}
