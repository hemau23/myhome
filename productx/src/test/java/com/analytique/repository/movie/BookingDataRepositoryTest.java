package com.analytique.repository.movie;

import com.analytique.entity.movie.BookingData;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class BookingDataRepositoryTest extends IntegrationFlowTest{


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

        BookingData movieBookingData = new BookingData();

        movieBookingData.setDataCollectionDate(new Date());
        movieBookingData.setPrice(120);
        movieBookingData.setShowDate(new Date());
        movieBookingData.setAverageRating("80%");
        movieBookingData.setVote(4000);
        movieBookingDataRepository.save(movieBookingData);
        List<BookingData> all = movieBookingDataRepository.findAll();
        assertEquals(all.size(),1);

    }
}