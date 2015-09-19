package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.BookingRawData;
import com.analytique.entity.monitor.ErrorMessage;
import com.analytique.exception.AnalytiqueException;
import com.analytique.repository.bookingdata.BookingRawDataRepository;
import com.analytique.repository.movie.BookingDataRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.repository.theater.SeatClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class BookingDataTransformer implements GenericTransformer<List<BookingRawData>,String> {


    @Autowired
    BookingRawDataRepository bookingRawDataRepository;

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @Autowired
    BookingDataRepository bookingDataRepository;

    @Autowired
    SeatClassRepository seatClassRepository;
    



    @Override
    public String transform(List<BookingRawData> bookingRawDataList) {
        try {
            if (bookingRawDataList.size() < 1) return null;
            for (BookingRawData bookingRawData:bookingRawDataList){

            }
        }
        catch (Exception e) {
            new AnalytiqueException("Transformation failure" +e);
        }
        return null;
    }
}
