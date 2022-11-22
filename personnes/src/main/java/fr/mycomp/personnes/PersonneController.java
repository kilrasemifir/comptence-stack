package fr.mycomp.personnes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping("")
    public Iterable<Personne> getAllPersonnes() {
        return personneService.getAllPersonnes();
    }

    @PostMapping("")
    public Personne createPersonne(@RequestBody Personne personne) {
        return personneService.createPersonne(personne);
    }

    @GetMapping("/{id}")
    public Personne getPersonne(@PathVariable String id) {
        return personneService.getPersonne(id);
    }

    @PutMapping("")
    public Personne updatePersonne(@RequestBody Personne personne) {
        return personneService.updatePersonne(personne);
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable String id) {
        personneService.deletePersonne(id);
    }

}
