package org.example.ms_client.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CommandeDTO {
    private Long id;
    private String clientId;

    private String reference;

    private double montant;
}
