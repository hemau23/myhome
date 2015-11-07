package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.BookingRawData;
import com.analytique.entity.movie.BookingData;
import com.analytique.repository.bookingdata.BookingRawDataRepository;
import com.analytique.repository.movie.BookingDataRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by hemant on 9/19/2015.
 */
public class BookingDataTransformerTest extends IntegrationFlowTest{

    @Autowired
    BookingRawDataRepository bookingRawDataRepository;

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @Autowired
    BookingDataRepository bookingDataRepository;

    BookingRawData bookingRawData;

    @Autowired
    BookingDataTransformer bookingDataTransformer;


    @BeforeMethod
    public void setUp() throws Exception {
        bookingDataRepository.deleteAll();
        movieInformationRepository.deleteAll();
        bookingRawDataRepository.deleteAll();
        bookingRawData= new BookingRawData();
        bookingRawData.setMovieInformationId("adsadf");

        bookingRawData.setExternalTheaterCode("AMC");
        bookingRawData.setVote(12);
        bookingRawData.setAverageRating("20%");
        bookingRawData.setDataCollectionDate(new Date());
        bookingRawData.setShowDate(new Date());
        bookingRawData.setPrice(120);
        bookingRawData.setClassName("PLATINUM");
        bookingRawData.setSeatCode("PLNM");
        bookingRawData.setRowName("A");
        bookingRawData.setSeatMap("|1:J:A303:A304:A305:A306:A307:A308:A309:A310:A211:A212:A213:A214:A000:A000:A000|");
    }

    @Test
    public void testTransform() throws Exception {
        List<BookingRawData> bookingRawDataList= new ArrayList<>();
        bookingRawDataList.add(bookingRawData);
        List<BookingData> transform = bookingDataTransformer.transform(bookingRawDataList);
        assertEquals(transform.size(),1);
    }
}