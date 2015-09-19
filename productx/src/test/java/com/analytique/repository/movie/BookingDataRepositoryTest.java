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
    BookingDataRepository bookingDataRepository;

    @BeforeMethod
    public void setUp() throws Exception {
        bookingDataRepository.deleteAll();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    void testSave(){
        BookingData movieBookingData = new BookingData();
        movieBookingData.setDataCollectionDate(new Date());
        movieBookingData.setPrice(120);
        movieBookingData.setShowDate(new Date());
        movieBookingData.setAverageRating("80%");
        movieBookingData.setVote(4000);
        movieBookingData.setMovieInformationId("sdh67");
        movieBookingData.setCapacity(120);
        movieBookingData.setOccupied(20);
        movieBookingData.setRowName("A");
        movieBookingData.setSeatClassId("443tf");
        movieBookingData.setVote(123344);
        movieBookingData.setDataCollectionDate(new Date());
        movieBookingData.setShowDate(new Date());
        movieBookingData.setTheaterId("fhgvhj");
        bookingDataRepository.save(movieBookingData);
        List<BookingData> all = bookingDataRepository.findAll();
        assertEquals(all.size(),1);
    }
}