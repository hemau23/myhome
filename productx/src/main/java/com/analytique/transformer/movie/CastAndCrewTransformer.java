package com.analytique.transformer.movie;

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
            List<CastAndCrew> castAndCrewList=createOrUpdateCastAndCrew(movieInformation);
            if (castAndCrewList.size()>0)
            castAndCrews.addAll(castAndCrewList);
        }
        return castAndCrews;
    }

    private List<CastAndCrew> createOrUpdateCastAndCrew(MovieInformation movieInformation) {
        List<CastAndCrew> castAndCrewList = new ArrayList<CastAndCrew>();
        String crewInfo = movieInformation.getCrew();
        String[] castAndRoles = crewInfo.split("\\|");
        for (String castAndRole: castAndRoles){
            String[] crewList = castAndRole.split(":");
            assert crewList.length == 2 : " cast and roll not defined correctly";
            String roleName =crewList[0];
            for (String name: crewList[1].split(",")) {
                CastAndCrew castAndCrew = new CastAndCrew();
                Person person = personRepository.findByName(name);
                if (person == null) {
                    person = new Person();
                    person.setPersonRating(1);
                    person.setName(name);
                    person = personRepository.save(person);
                }
                castAndCrew.setPersonId(person.getPersonId());
                Role role = roleRepository.findByRoleName(roleName);
                if (role == null) {
                    role = new Role();
                    role.setRoleName(roleName);
                    role = roleRepository.save(role);
                }
                castAndCrew.setMovieInformationId(movieInformation.getMovieInformationId());
                castAndCrew.setRoleId(role.getRoleId());
                castAndCrewList.add(castAndCrew);
            }
        }
        return castAndCrewList;

    }
}
