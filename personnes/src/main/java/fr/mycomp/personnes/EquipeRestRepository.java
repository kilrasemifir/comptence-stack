package fr.mycomp.personnes;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class EquipeRestRepository {

    private RestTemplate restTemplate = new RestTemplate();

    public void addToEquipe(Personne personne) {
        String url = "http://localhost:3000/api/equipes/"+personne.getEquipeId()+"/personnes";
        System.out.println("url = " + url);
        restTemplate.put(
                url
                , personne, Void.class);
    }

    public void removeToEquipe(Personne personne) {
        String url = "http://localhost:3000/api/equipes/"+personne.getEquipeId()+"/personnes/"+personne.getId();
        restTemplate.delete(
                url
                , personne, Void.class);
    }
}
