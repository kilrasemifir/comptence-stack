package fr.mycomp.personnes;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonneService {

    private final PersonneRepository personneRepository;
    private final EquipeRestRepository equipeRestRepository;

    public PersonneService(PersonneRepository personneRepository, EquipeRestRepository equipeRestRepository) {
        this.personneRepository = personneRepository;
        this.equipeRestRepository = equipeRestRepository;
    }

    public Personne createPersonne(Personne personne) {
        var newPersonne = personneRepository.save(personne);
        equipeRestRepository.addToEquipe(newPersonne);
        return newPersonne;
    }

    public Personne getPersonne(String id) {
        return personneRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Personne updatePersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public void deletePersonne(String id) {
        Personne personne = getPersonne(id);
        this.equipeRestRepository.removeToEquipe(personne);
        personneRepository.deleteById(id);
    }

    public Iterable<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }
}
