package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.repository.bookingdata.MovieRawInformationRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class MovieInformationTransformerTest extends IntegrationFlowTest {

    @Autowired
    MovieInformationTransformer movieInformationTransformer;
    List<MovieRawInformation> movieRawInformations;
    @BeforeMethod
    public void setUp() throws Exception {
        MovieRawInformation movieRawInformation = new MovieRawInformation();
        movieRawInformation.setCertificate("U/A");
        movieRawInformation.setMovieName("brothers");
        movieRawInformation.setReleaseDate(new Date());
        movieRawInformation.setIsHitSongs(true);
        movieRawInformation.setCrew("Salman khan:Hero|Neha Dhupia:heroin|Anu Malik:Music Director");
        movieRawInformation.setDuration(120);
        String genres = "comedy|action";
        movieRawInformation.setGenres(genres);
        movieRawInformations= new ArrayList<>();
        movieRawInformations.add(movieRawInformation);

    }

    @Test
    public void testTransform() throws Exception {
        List<MovieInformation> transform = movieInformationTransformer.transform(movieRawInformations);
        assertEquals(transform.size(),1);
    }
}