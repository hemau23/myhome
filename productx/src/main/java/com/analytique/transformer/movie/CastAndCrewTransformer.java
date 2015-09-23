package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.crew.Person;
import com.analytique.entity.crew.Role;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.repository.crew.PersonRepository;
import com.analytique.repository.crew.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hemant on 9/22/2015.
 */

@Component
public class CastAndCrewTransformer implements GenericTransformer<List<MovieInformation>,String> {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public String transform(List<MovieInformation> source) {

        for (MovieInformation movieInformation:source){
            createOrUpdateCastAndCrew(movieInformation);
        }
        return "completed";
    }

    private void createOrUpdateCastAndCrew(MovieInformation movieInformation) {
        String castAndCrew = movieInformation.getCrew();
        String[] castAndRoles = castAndCrew.split("|");
        for (String castAndRole: castAndRoles){
            String[] value = castAndRole.split(":");
            assert value.length < 2 : " cast and roll defiend correctly";
            String[] personData =value[0].split(" ");

            Person person = personRepository.findByFirstNameAndLastName(personData[0], personData[1]);
            if (person == null) {
                person = new Person();
                person.setFirstName(personData[0]);
                person.setLastName(personData[1]);
                personRepository.save(person);
            }
            Role role = roleRepository.findByRoleName(value[1]);
            if (role == null){
                role=new Role();
                role.setRoleName(value[1]);
                roleRepository.save(role);
            }
        }
    }
}
