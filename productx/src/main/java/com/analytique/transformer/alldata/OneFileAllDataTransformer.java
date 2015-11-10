package com.analytique.transformer.alldata;

import com.analytique.entity.alldata.OneFileAllData;
import com.analytique.entity.bookingdata.BookingRawData;
import com.analytique.entity.movie.BookingData;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.entity.theater.SeatClass;
import com.analytique.entity.theater.TheaterInformation;
import com.analytique.exception.AnalytiqueException;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.repository.theater.SeatClassRepository;
import com.analytique.repository.theater.TheaterInformationRepository;
import com.analytique.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hemau23 on 11/7/2015.
 */
@Component
public class OneFileAllDataTransformer implements GenericTransformer<List<OneFileAllData>,List<BookingData>> {

    private static final Logger logger = LoggerFactory.getLogger(OneFileAllDataTransformer.class);
    public static final String OCCUPIED = "occupied";
    public static final String CAPACITY = "capacity";
    public static final char NO_SEAT = '0';
    public static char BOOKED = '3';
    public static char AVAILABLE = '1';
    public static final String CDT_DATE_TIME_FORMAT = "E MMM dd HH:mm:ss Z yyyy";
    public static final String AM_PM_DATE_TIME_FORMAT = "yyyyMMdd hh:mm a";
    public static final String INDIA_TIMEZONE = "Asia/Kolkata";

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @Autowired
    TheaterInformationRepository theaterInformationRepository;

    @Autowired
    SeatClassRepository seatClassRepository;


    @Override
    public List<BookingData> transform(List<OneFileAllData> source) {
        List<BookingData> bookingDataList= new ArrayList<>();
        for (OneFileAllData oneFileAllData:source) {
            MovieInformation movieInformation = saveMovieInformation(oneFileAllData);
            TheaterInformation theaterInformation = saveTheaterInformation(oneFileAllData);
            bookingDataList.addAll(saveBookingInformation(oneFileAllData, movieInformation, theaterInformation));
        }
        return bookingDataList;
    }

    private List<BookingData> saveBookingInformation(OneFileAllData oneFileAllData, MovieInformation movieInformation, TheaterInformation theaterInformation) {


        List<BookingData> bookingDataList = new ArrayList<>();

        String showDateTimeStr = oneFileAllData.getShowDateTime().trim();
        try {
            Date showDateTime = null;
            if (showDateTimeStr.contains("AM") || showDateTimeStr.contains("PM")) {
                showDateTime = DateTimeUtil.parseDate(showDateTimeStr, "yyyyMMdd hh:mm a");
            } else if (showDateTimeStr.contains("CDT")) {
                Date date = DateTimeUtil.parseDate(showDateTimeStr, CDT_DATE_TIME_FORMAT);
                String india = DateTimeUtil.buildDateWithTimeZone(date, INDIA_TIMEZONE, AM_PM_DATE_TIME_FORMAT);
                showDateTime = DateTimeUtil.parseDate(india, "yyyyMMdd hh:mm a");
            }
            if (showDateTime == null) throw new AnalytiqueException("Show date time parsing error" + showDateTimeStr);
            String[] split = oneFileAllData.getSeatMap().split("\\|\\|");
            Map<String,String> seatClassMap=getSeatClass(split[0]);
            for (String seatClassName :seatClassMap.keySet()){
                BookingData bookingData = new BookingData();
                bookingData.setTheaterId(theaterInformation.getTheaterId());
                bookingData.setMovieInformationId(movieInformation.getMovieInformationId());
                bookingData.setShowDate(showDateTime);
                bookingData.setSeatClassId(getSeatClassId(seatClassName.trim()));
                Map<String, Integer> occupancyAndCapacityForSeatClass = getOccupancyAndCapacityForSeatClass(seatClassMap.get(seatClassName), split[1].trim());
                bookingData.setCapacity(occupancyAndCapacityForSeatClass.get(CAPACITY));
                bookingData.setOccupied(occupancyAndCapacityForSeatClass.get(OCCUPIED));
                bookingDataList.add(bookingData);
            }

        }catch (Exception e){
            throw new AnalytiqueException("booking Data parsing error" +e + showDateTimeStr + " "+ theaterInformation.getTheaterId()+ " "+ movieInformation.getMovieName());
        }
        return bookingDataList;
    }

    private  Map<String, Integer>  getOccupancyAndCapacityForSeatClass(String classLetter, String seatMap) {
        Map<String, Integer> finalMapForCapacityAndOccupancy= new HashMap<>();
        finalMapForCapacityAndOccupancy.put(OCCUPIED,0);
        finalMapForCapacityAndOccupancy.put(CAPACITY,0);
        for (String str : seatMap.split("\\|")) {
            Pattern pattern = Pattern.compile(classLetter+"\\d+");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                Map<String, Integer> stringIntegerMap = calculateOccupancy(str);
                finalMapForCapacityAndOccupancy.put(CAPACITY,finalMapForCapacityAndOccupancy.get(CAPACITY)+stringIntegerMap.get(CAPACITY));
                finalMapForCapacityAndOccupancy.put(OCCUPIED,finalMapForCapacityAndOccupancy.get(OCCUPIED)+stringIntegerMap.get(OCCUPIED));
            }
        }
        return finalMapForCapacityAndOccupancy;
    }

    private String getSeatClassId(String seatClassName) {
        SeatClass seatClass = seatClassRepository.findBySeatCode(seatClassName);
        if (seatClass == null) {
            seatClass = new SeatClass();
            seatClass.setClassName(seatClassName);
            seatClass.setSeatCode(seatClassName);
            seatClass=seatClassRepository.save(seatClass);
        }
        return seatClass.getSeatClassId();
    }

    private TheaterInformation saveTheaterInformation(OneFileAllData oneFileAllData) {
        TheaterInformation theaterInformation  = theaterInformationRepository.findByExternalTheaterCode(oneFileAllData.getExternalTheaterCode().trim());
        if(theaterInformation == null) {
            theaterInformation = new TheaterInformation();
            theaterInformation.setExternalTheaterCode(oneFileAllData.getExternalTheaterCode().trim());
            theaterInformation=theaterInformationRepository.save(theaterInformation);
        }
        return theaterInformation;
    }

    private MovieInformation saveMovieInformation(OneFileAllData oneFileAllData) {

        MovieInformation movieInformation = movieInformationRepository.findByMovieExternalCode(oneFileAllData.getMovieExternalCode());
        if (movieInformation == null) {
            movieInformation = new MovieInformation();
            movieInformation.setMovieName(oneFileAllData.getMovieName().trim());
            movieInformation.setMovieExternalCode(oneFileAllData.getMovieExternalCode().trim());
            movieInformation=movieInformationRepository.save(movieInformation);
        }
        return movieInformation;
    }

    private static Map<String, String> getSeatClass(String seatClassStr) {
        Map<String, String> seatClass = new HashMap<>();
        try {
            String[] splitStr = seatClassStr.split("\\|");
            for (String str : splitStr) {
                seatClass.put(str.split(":")[0], str.split(":")[1]);
            }
        }catch (Exception e){
            throw new AnalytiqueException("Failed while getting seatClass from " + e + seatClassStr);
        }
        return seatClass;
    }
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private Map<String,Integer> calculateOccupancy(String seatMap) {
        HashMap<String, Integer> occupancyAndCapacity = new HashMap<>();
        if(seatMap!=null) {
            String[] seatCodes =seatMap.split(":");
            Integer capacity=0;
            Integer occupied=0;
            if (!isNumeric(seatCodes[0].trim())) {
                BOOKED ='2';
                AVAILABLE ='1';
            }
            for (int i=2;i<seatCodes.length;i++){
                char c = seatCodes[i].charAt(1);
                if (c==BOOKED){
                    occupied++;
                    capacity++;
                }
                else if (c==AVAILABLE){
                    capacity++;
                }
                else if (c== NO_SEAT){
                    logger.debug("Seat Not available");
                }
                else {
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
