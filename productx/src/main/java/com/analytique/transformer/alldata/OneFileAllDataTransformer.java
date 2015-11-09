package com.analytique.transformer.alldata;

import com.analytique.entity.alldata.OneFileAllData;
import com.analytique.entity.bookingdata.BookingRawData;
import com.analytique.entity.movie.BookingData;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.entity.theater.TheaterInformation;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.repository.theater.TheaterInformationRepository;
import com.analytique.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hemau23 on 11/7/2015.
 */
public class OneFileAllDataTransformer implements GenericTransformer<List<OneFileAllData>,List<BookingData>> {

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @Autowired
    TheaterInformationRepository theaterInformationRepository;


    @Override
    public List<BookingData> transform(List<OneFileAllData> source) {
        List<BookingData> bookingDataList= new ArrayList<>();
        for (OneFileAllData oneFileAllData:source) {
            MovieInformation movieInformation = saveMovieInformation(oneFileAllData);
            TheaterInformation theaterInformation = saveTheaterInformation(oneFileAllData);
            bookingDataList.add(saveBookingInformation(oneFileAllData, movieInformation, theaterInformation));
        }
        return bookingDataList;
    }

    private BookingData saveBookingInformation(OneFileAllData oneFileAllData, MovieInformation movieInformation, TheaterInformation theaterInformation) {

        BookingData bookingData = new BookingData();

        bookingData.setTheaterId(theaterInformation.getTheaterId());
        bookingData.setMovieInformationId(movieInformation.getMovieInformationId());
        String showDateTimeStr = oneFileAllData.getShowDateTime();
        Date showDateTime;
        if(showDateTimeStr.contains("AM") || showDateTimeStr.contains("PM")){
            showDateTime = DateTimeUtil.parseDate(showDateTimeStr, "yyyyMMdd hh:mm a");
        }
        else if (showDateTimeStr.contains("CDT")){
            DateTimeUtil.
        }

        //bookingData.setShowDate(oneFileAllData.);

        return bookingData;
    }

    private TheaterInformation saveTheaterInformation(OneFileAllData oneFileAllData) {
        TheaterInformation theaterInformation  = theaterInformationRepository.findByExternalTheaterCode(oneFileAllData.getExternalTheaterCode());
        if(theaterInformation == null) {
            theaterInformation = new TheaterInformation();
            theaterInformation.setExternalTheaterCode(oneFileAllData.getExternalTheaterCode());
            theaterInformation=theaterInformationRepository.save(theaterInformation);
        }
        return theaterInformation;
    }

    private MovieInformation saveMovieInformation(OneFileAllData oneFileAllData) {

        MovieInformation movieInformation = movieInformationRepository.findByMovieExternalCode(oneFileAllData.getMovieExternalCode());
        if (movieInformation == null) {
            movieInformation = new MovieInformation();
            movieInformation.setMovieName(oneFileAllData.getMovieName());
            movieInformation.setMovieExternalCode(oneFileAllData.getMovieExternalCode());
            movieInformation=movieInformationRepository.save(movieInformation);
        }
        return movieInformation;
    }
}
