package org.example.ms_commande.mappers;

import org.example.ms_commande.dto.CommandeDTO;
import org.example.ms_commande.entities.Commande;

public class CommandeMapper {

    public static CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setClientId(commande.getClientId());
        dto.setMontant(commande.getMontant());
        dto.setReference(commande.getReference());
        return dto;
    }

    public static Commande toEntity(CommandeDTO dto) {
        Commande commande = new Commande();
        commande.setId(dto.getId());
        commande.setClientId(dto.getClientId());
        commande.setMontant(dto.getMontant());
        commande.setReference(dto.getReference());
        return commande;
    }
}
