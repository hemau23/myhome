package com.analytique.repository.movie;

import com.analytique.entity.movie.CastAndCrew;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by hemant on 9/19/2015.
 */
public class CastAndCrewRepositoryTest  extends IntegrationFlowTest {


    @Autowired
    CastAndCrewRepository castAndCrewRepository;

    @BeforeMethod
    public void setUp() throws Exception {
        castAndCrewRepository.deleteAll();
    }

    @Test
    void testSaveAll(){
        CastAndCrew castAndCrew = new CastAndCrew();
        castAndCrew.setMovieInformationId("sdc32");
        castAndCrew.setPersonId("aad233");
        castAndCrew.setRolId("ds099");
        castAndCrewRepository.save(castAndCrew);
        List<CastAndCrew> all = castAndCrewRepository.findAll();
        assertEquals(all.size(),1);

    }
}