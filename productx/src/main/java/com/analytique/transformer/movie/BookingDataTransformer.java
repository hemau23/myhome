package com.analytique.transformer.movie;
import com.analytique.entity.bookingdata.BookingRawData;

import com.analytique.entity.movie.BookingData;
import com.analytique.entity.theater.SeatClass;
import com.analytique.entity.theater.TheaterInformation;
import com.analytique.exception.AnalytiqueException;
import com.analytique.repository.bookingdata.BookingRawDataRepository;
import com.analytique.repository.movie.BookingDataRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.repository.theater.SeatClassRepository;
import com.analytique.repository.theater.TheaterInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class BookingDataTransformer implements GenericTransformer<List<BookingRawData>,List<BookingData>> {

    private static final Logger logger = LoggerFactory.getLogger(BookingDataTransformer.class);
    public static final String OCCUPIED = "occupied";
    public static final String CAPACITY = "capacity";
    public static final char BOOKED = '3';
    public static final char AVAILABLE = '2';
    public static final char AVAILABLE_NOT = '1';
    public static final char BLANK_SPACE = '0';

    @Autowired
    BookingRawDataRepository bookingRawDataRepository;

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @Autowired
    BookingDataRepository bookingDataRepository;

    @Autowired
    SeatClassRepository seatClassRepository;

    @Autowired
    TheaterInformationRepository theaterInformationRepository;




    @Override
    public List<BookingData> transform(List<BookingRawData> bookingRawDataList) {
        List<BookingData> bookingDataList= new ArrayList<>();

        try {
            if (bookingRawDataList.size() < 1) return bookingDataList;
            for (BookingRawData bookingRawData:bookingRawDataList){             
                
                BookingData bookingData = new BookingData();
                
                // calculate occupancy
                Map<String, Integer> occupancyAndCapacity = calculateOccupancy(bookingRawData);
                if (occupancyAndCapacity!=null) {
                    bookingData.setOccupied(occupancyAndCapacity.get(OCCUPIED));
                    bookingData.setCapacity(occupancyAndCapacity.get(CAPACITY));
                    SeatClass seatClass = seatClassRepository.findBySeatCode(bookingRawData.getSeatCode());
                    if (seatClass == null) {
                        throw new AnalytiqueException("SeatClass not found");
                    }
                    bookingData.setSeatClassId(seatClass.getSeatClassId());
                    TheaterInformation theaterInformation = theaterInformationRepository.findByExternalTheaterCode(bookingRawData.getExternalTheaterCode());
                    if (theaterInformation == null) {
                        throw new AnalytiqueException("TheaterInformation not found");
                    }
                    bookingData.setMovieInformationId(theaterInformation.getTheaterId());
                    bookingData.setMovieInformationId(bookingRawData.getMovieInformationId());
                    bookingData.setDataCollectionDate(bookingRawData.getDataCollectionDate());
                    bookingData.setVote(bookingRawData.getVote());
                    bookingData.setShowDate(bookingRawData.getShowDate());
                    bookingData.setAverageRating(bookingRawData.getAverageRating());
                    bookingData.setPrice(bookingRawData.getPrice());
                    bookingData.setRowName(bookingRawData.getRowName());
                    bookingDataList.add(bookingData);
                }
                
            }
        }
        catch (Exception e) {
          logger.error("Transformation failure " +e);
        }
        return bookingDataList;
    }

    private Map<String,Integer> calculateOccupancy(BookingRawData bookingRawData) {
        HashMap<String, Integer> occupancyAndCapacity = new HashMap<>();
        String seatMap = bookingRawData.getSeatMap();
        if(seatMap!=null) {
            String[] seatCodes =seatMap.split(":");
            Integer capacity=0;
            Integer occupied=0;
            for (int i=2;i<seatCodes.length;i++){
                char c = seatCodes[i].charAt(1);
                switch (seatCodes[i].charAt(1)) {
                    case BOOKED:
                        occupied++;
                        capacity++;
                        continue;
                    case AVAILABLE:
                        capacity++;
                        continue;
                    case AVAILABLE_NOT:
                        capacity++;
                        continue;
                    case BLANK_SPACE:
                        logger.debug("Seat Not available");
                        continue;
                    default:
                        logger.warn("SeatCode not find.Please check your data" + seatCodes[i] );

                }
            }

            occupancyAndCapacity.put(OCCUPIED,occupied);
            occupancyAndCapacity.put(CAPACITY,capacity);
            return occupancyAndCapacity; 
        }
        return null;
    }
}
