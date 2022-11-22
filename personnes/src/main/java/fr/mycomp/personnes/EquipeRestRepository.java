package fr.mycomp.personnes;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Classe de communication avec l'API Equipe
 *
 * @author killian Raoux
 */
@Repository
public class EquipeRestRepository {

    /**
     * RestTemplate est une classe de Spring qui permet de faire des requêtes HTTP
     */
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Ajoute une personne à l'équipe
     * @param personne la personne à ajouter.
     * @throws IllegalArgumentException si la personne ne possède pas d'identifiant d'équipe.
     */
    public void addToEquipe(Personne personne) {
        if (personne == null) throw  new IllegalArgumentException("La personne ne peut pas être null");
        if (personne.getEquipeId()==null) throw new IllegalArgumentException("La personne n'a pas d'équipe");

        String url = "http://localhost:3000/api/equipes/"+personne.getEquipeId()+"/personnes";
        restTemplate.put( url , personne, Void.class);
    }

    /**
     * Supprime une personne de l'équipe
     * @param personne la personne à supprimer.
     * @throws IllegalArgumentException si la personne ne possède pas d'identifiant d'équipe.
     *
     */
    public void removeToEquipe(Personne personne) {
        if (personne == null) throw  new IllegalArgumentException("La personne ne peut pas être null");
        if (personne.getEquipeId()==null) throw new IllegalArgumentException("La personne n'a pas d'équipe");
        String url = "http://localhost:3000/api/equipes/"+personne.getEquipeId()+"/personnes/"+personne.getId();
        restTemplate.delete( url , personne, Void.class);
    }
}
