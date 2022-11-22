package fr.mycomp.personnes;

import org.springframework.web.bind.annotation.*;


/**
 * Classe de communication avec l'API Equipe
 *
 * @author killian Raoux
 */
@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * GET /personnes : Récupère toutes les personnes
     *
     * @return
     */
    @GetMapping("")
    public Iterable<Personne> getAllPersonnes() {
        return personneService.getAllPersonnes();
    }

    /**
     * POST /personnes : Crée une personne
     * @param personne la personne à créer
     * @return la personne créée
     */
    @PostMapping("")
    public Personne createPersonne(@RequestBody Personne personne) {
        return personneService.createPersonne(personne);
    }

    /**
     * GET /personnes/{id} : Met à jour une personne
     * @param id l'identifiant de la personne à mettre à jour
     * @return la personne mise à jour
     * @throws org.springframework.web.server.ResponseStatusException si la personne n'existe pas
     */
    @GetMapping("/{id}")
    public Personne getPersonne(@PathVariable String id) {
        return personneService.getPersonne(id);
    }

    /**
     * PUT /personnes/{id} : Met à jour une personne
     * @param personne la personne à mettre à jour
     * @return la personne mise à jour
     */
    @PutMapping("")
    public Personne updatePersonne(@RequestBody Personne personne) {
        return personneService.updatePersonne(personne);
    }

    /**
     * DELETE /personnes/{id} : Supprime une personne
     * @param id l'identifiant de la personne à supprimer
     */
    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable String id) {
        personneService.deletePersonne(id);
    }

}
