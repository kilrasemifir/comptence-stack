package fr.mycomp.personnes;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Personne {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String status;
    private String equipeId;

    @DBRef
    private List<Personne> amis = new ArrayList<>();
}
