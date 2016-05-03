package com.analytique.entity.movie;

import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.util.DateTimeUtil;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class MovieInformationTest  extends IntegrationFlowTest {


    @Autowired
    MovieInformationRepository movieInformationRepository;

    @BeforeMethod
    public void setUp() throws Exception {
        movieInformationRepository.deleteAll();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    void testSaveAll(){

        MovieInformation movieInformation = new MovieInformation();
        movieInformation.setCertificate("U/A");
        movieInformation.setDuration("120");
        movieInformation.setIsHitSongs(true);
        movieInformation.setReleaseDate("2015-01-01");
        movieInformation.setMovieName("Talvaar");
        movieInformationRepository.save(movieInformation);
        List<MovieInformation> all = movieInformationRepository.findAll();
        assertEquals(all.size(),1);

    }


}