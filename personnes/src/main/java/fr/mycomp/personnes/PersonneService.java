package fr.mycomp.personnes;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service de gestion des personnes
 */
@Service
public class PersonneService {

    private final PersonneRepository personneRepository;
    private final EquipeRestRepository equipeRestRepository;

    public PersonneService(PersonneRepository personneRepository, EquipeRestRepository equipeRestRepository) {
        this.personneRepository = personneRepository;
        this.equipeRestRepository = equipeRestRepository;
    }

    /**
     * Sauvegarde une personne dans la base de données et met à jour l'équipe
     * @param personne la personne à sauvegarder
     * @return la personne sauvegardée
     */
    public Personne createPersonne(Personne personne) {
        var newPersonne = personneRepository.save(personne);
        equipeRestRepository.addToEquipe(newPersonne);
        return newPersonne;
    }

    /**
     * Retourne la personne possédant l'identifiant donné
     * @param id l'identifiant de la personne à mettre à jour
     * @return la personne mise à jour
     */
    public Personne getPersonne(String id) {
        return personneRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Met à jour une personne dans la base de données et met à jour l'équipe
     * @param personne la personne à mettre à jour
     * @return la personne mise à jour
     */
    public Personne updatePersonne(Personne personne) {
        Personne oldPersonne = getPersonne(personne.getId());
        if (personne.getEquipeId() != null && !personne.getEquipeId().equals(oldPersonne.getEquipeId())) {
            equipeRestRepository.removeToEquipe(oldPersonne);
            equipeRestRepository.addToEquipe(personne);
        }
        return personneRepository.save(personne);
    }

    /**
     * Supprime une personne dans la base de données et met à jour l'équipe
     * @param id l'identifiant de la personne à mettre à jour
     */
    public void deletePersonne(String id) {
        Personne personne = getPersonne(id);
        this.equipeRestRepository.removeToEquipe(personne);
        personneRepository.deleteById(id);
    }

    /**
     * Retourne toutes les personnes
     * @return toutes les personnes
     */
    public Iterable<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    public Personne patch(Personne personne) {
        Personne oldPersonne = getPersonne(personne.getId());
        if (personne.getNom() != null) {
            oldPersonne.setNom(personne.getNom());
        }
        if (personne.getPrenom() != null) {
            oldPersonne.setPrenom(personne.getPrenom());
        }
        if (personne.getEquipeId() != null) {
            oldPersonne.setEquipeId(personne.getEquipeId());
        }
        return updatePersonne(oldPersonne);
    }
}
