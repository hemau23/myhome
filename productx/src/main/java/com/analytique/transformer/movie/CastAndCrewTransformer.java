package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.crew.Person;
import com.analytique.entity.crew.Role;
import com.analytique.entity.movie.CastAndCrew;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.repository.crew.PersonRepository;
import com.analytique.repository.crew.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemant on 9/22/2015.
 */

@Component
public class CastAndCrewTransformer implements GenericTransformer<List<MovieInformation>,List<CastAndCrew>> {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<CastAndCrew> transform(List<MovieInformation> source) {
        List<CastAndCrew> castAndCrews= new ArrayList<CastAndCrew>();
        for (MovieInformation movieInformation:source){
            CastAndCrew castAndCrew=createOrUpdateCastAndCrew(movieInformation);
            castAndCrews.add(castAndCrew);
        }
        return castAndCrews;
    }

    private CastAndCrew createOrUpdateCastAndCrew(MovieInformation movieInformation) {
        CastAndCrew castAndCrew = new CastAndCrew();
        String crewInfo = movieInformation.getCrew();
        String[] castAndRoles = crewInfo.split("\\|");
        for (String castAndRole: castAndRoles){
            String[] value = castAndRole.split(":");
            assert value.length == 2 : " cast and roll not defined correctly";
            String[] personData =value[0].split(" ");

            Person person = personRepository.findByFirstNameAndLastName(personData[0], personData[1]);
            if (person == null) {
                person = new Person();
                person.setFirstName(personData[0]);
                person.setLastName(personData[1]);
                person = personRepository.save(person);
            }
            castAndCrew.setPersonId(person.getPersonId());
            Role role = roleRepository.findByRoleName(value[1]);
            if (role == null){
                role=new Role();
                role.setRoleName(value[1]);
                role = roleRepository.save(role);
            }
            castAndCrew.setRolId(role.getRolId());
        }
        return castAndCrew;

    }
}
