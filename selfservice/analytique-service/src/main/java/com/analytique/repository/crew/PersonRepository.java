package com.analytique.repository.crew;

import com.analytique.entity.crew.Person;
import com.analytique.entity.movie.MovieInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/22/2015.
 */

@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends MongoRepository<Person,String> {

    Person findByName(@Param("name") String name);
    Person findByPersonId(@Param("personId") String personId);

}
