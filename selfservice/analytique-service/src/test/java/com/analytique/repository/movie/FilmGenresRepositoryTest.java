package com.analytique.repository.movie;

import com.analytique.entity.movie.CastAndCrew;
import com.analytique.entity.movie.FilmGenres;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by hemant on 9/19/2015.
 */
public class FilmGenresRepositoryTest  extends IntegrationFlowTest {

    @Autowired
    FilmGenresRepository filmGenresRepository;

    @BeforeMethod
    public void setUp() throws Exception {
        filmGenresRepository.deleteAll();
    }

    @Test
    void testSaveAll(){
        FilmGenres filmGenres = new FilmGenres();
        filmGenres.setGenreId("sdamkadk2332");
        filmGenres.setMovieId("sda2");
        filmGenresRepository.save(filmGenres);
        List<FilmGenres> all = filmGenresRepository.findAll();
        assertEquals(all.size(),1);
    }
}