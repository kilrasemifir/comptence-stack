package fr.mycomp.personnes;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PersonneRepository extends MongoRepository<Personne, String> {

}
