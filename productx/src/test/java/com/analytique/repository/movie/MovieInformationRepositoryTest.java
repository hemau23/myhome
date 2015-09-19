package com.analytique.repository.movie;

import com.analytique.entity.movie.MovieInformation;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by hemant on 9/19/2015.
 */
public class MovieInformationRepositoryTest  extends IntegrationFlowTest {

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @BeforeMethod
    public void setUp() throws Exception {
        movieInformationRepository.deleteAll();
    }

    @Test
    void testSaveAll(){
        MovieInformation movieInformation = new MovieInformation();
        movieInformation.setCertificate("U/A");
        movieInformation.setDuration(120);
        movieInformation.setIsHitSongs(true);
        movieInformation.setMovieName("Brother");
        movieInformation.setReleaseDate(new Date());
        movieInformationRepository.save(movieInformation);
        List<MovieInformation> all = movieInformationRepository.findAll();
        assertEquals(all.size(),1);
    }
}