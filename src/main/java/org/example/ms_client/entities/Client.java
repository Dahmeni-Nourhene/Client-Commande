package org.example.ms_client.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@Getter
@Setter
public class Client {

    @Id

    private String id;
    private String nom;
    private String email;
    private String adresse;
}
