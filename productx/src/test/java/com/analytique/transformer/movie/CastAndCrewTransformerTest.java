package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.testng.Assert.*;

public class CastAndCrewTransformerTest extends IntegrationFlowTest{

    @Autowired
    CastAndCrewTransformer castAndCrewTransformer;
    private ArrayList<MovieInformation> movieInformations;

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

    }

    @Test
    public void testTransform() throws Exception {
        String transform = castAndCrewTransformer.transform(movieInformations);
        assertEquals(transform,"completed");
    }
}