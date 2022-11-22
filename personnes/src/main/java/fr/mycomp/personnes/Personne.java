package fr.mycomp.personnes;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une personne
 *
 * l'annotation @Document permet de définir le nom de la collection dans la base de données
 *
 * l'anotation @Data permet de générer les getters et setters, le constructeur, la méthode equals et la méthode hashCode
 *
 * @author killian Raoux
 */
@Data
@Document(collection = "personnes")
public class Personne {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String status;
    private String equipeId;
}
