package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.movie.CastAndCrew;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.repository.bookingdata.MovieRawInformationRepository;
import com.analytique.repository.crew.PersonRepository;
import com.analytique.repository.crew.RoleRepository;
import com.analytique.repository.movie.CastAndCrewRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class CastAndCrewTransformerTest extends IntegrationFlowTest{

    @Autowired
    CastAndCrewTransformer castAndCrewTransformer;
    private ArrayList<MovieInformation> movieInformations;

    @Autowired
    CastAndCrewRepository castAndCrewRepository;

    @Autowired
    MovieRawInformationRepository movieRawInformationRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @BeforeMethod
    public void setUp() throws Exception {
        MovieInformation movieInformation = new MovieInformation();
        movieInformation.setMovieInformationId("werw32");
        movieInformation.setCertificate("U/A");
        movieInformation.setMovieName("brothers");
        movieInformation.setReleaseDate(new Date());
        movieInformation.setIsHitSongs(true);
        movieInformation.setCrew("Salman khan:Hero|Neha Dhupia:heroin|Anu Malik:Music Director");
        movieInformation.setDuration(120);
        ArrayList<String> genres = new ArrayList<>();
        genres.add("comedy");
        genres.add("action");
        movieInformations= new ArrayList<>();
        movieInformations.add(movieInformation);
        castAndCrewRepository.deleteAll();
        movieInformationRepository.deleteAll();
        movieRawInformationRepository.deleteAll();
        roleRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void testTransform() throws Exception {
        List<CastAndCrew> transform = castAndCrewTransformer.transform(movieInformations);
        assertEquals(transform.size(),1);
    }
}