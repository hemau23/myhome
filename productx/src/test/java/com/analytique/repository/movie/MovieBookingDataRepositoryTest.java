package com.analytique.repository.movie;

import com.analytique.entity.movie.MovieBookingData;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class MovieBookingDataRepositoryTest extends IntegrationFlowTest{


    @Autowired
    MovieBookingDataRepository movieBookingDataRepository;

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
        movieBookingDataRepository.deleteAll();
    }

    @Test
    void testSave(){

        MovieBookingData movieBookingData = new MovieBookingData();
        movieBookingData.setCast("hemant,jaiswal");
        movieBookingData.setDataCollectionDate(new Date());
        movieBookingData.setDataCollectionTime("20:11:09");
        movieBookingData.setCertificate("U/A");
        movieBookingData.setDirectorName("david dhawan");
        movieBookingData.setDuration(120);
        movieBookingData.setMovieName("awjaar");
        movieBookingData.setPrice(120);
        movieBookingData.setSeatClassCode("PLATINUM");
        movieBookingData.setSeatClassName("PLATINUM");
        movieBookingData.setShowDate(new Date());
        movieBookingData.setShowTime("08:30");
        movieBookingData.setAverageRating("80%");
        movieBookingData.setVote(4000);
        movieBookingData.setTheaterCode("EA3499");
        movieBookingData.setSeat("A:22|A223");
        movieBookingData.setTheaterName("AMC");
        movieBookingData.setType("as");
        movieBookingData.setSeatClassRowCode("A");

        movieBookingDataRepository.save(movieBookingData);
        List<MovieBookingData> all = movieBookingDataRepository.findAll();
        assertEquals(all.size(),1);

    }
}