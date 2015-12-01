package com.analytique.repository.bookingdata;

import com.analytique.entity.bookingdata.BookingRawData;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;
public class BookingRawDataRepositoryTest extends IntegrationFlowTest {

    @Autowired
    BookingRawDataRepository bookingRawDataRepository;

    @BeforeMethod
    public void setUp() throws Exception {
        bookingRawDataRepository.deleteAll();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    void testSaveAll(){
        BookingRawData bookingRawData= new BookingRawData();
        bookingRawData.setMovieInformationId("adfssfd34");

        bookingRawData.setExternalTheaterCode("AMC");
        bookingRawData.setVote(12);
        bookingRawData.setAverageRating("20%");
        bookingRawData.setDataCollectionDate(new Date());
        bookingRawData.setShowDate("2015-01-01");
        bookingRawData.setPrice(120);
        bookingRawData.setClassName("PLATINUM");
        bookingRawData.setSeatCode("PLNM");
        bookingRawData.setRowName("A");
        bookingRawData.setSeatMap("|1:J:A303:A304:A305:A306:A307:A308:A309:A310:A211:A212:A213:A214:A000:A000:A000|");
        bookingRawDataRepository.save(bookingRawData);
        List<BookingRawData> all = bookingRawDataRepository.findAll();
        assertEquals(all.size(),1);
    }
}