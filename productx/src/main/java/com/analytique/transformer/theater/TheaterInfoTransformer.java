package com.analytique.transformer.theater;

import com.analytique.entity.theater.SeatClass;
import com.analytique.entity.theater.TheaterInformation;
import com.analytique.entity.theater.TheaterRawInformation;
import com.analytique.exception.AnalytiqueException;
import com.analytique.repository.theater.SeatClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemant on 9/27/2015.
 */

@Component
public class TheaterInfoTransformer implements GenericTransformer<List<TheaterRawInformation>,List<TheaterInformation>> {

    @Autowired
    SeatClassRepository seatClassRepository;
    @Override
    public List<TheaterInformation> transform(List<TheaterRawInformation> source) {
        List<TheaterInformation> theaterInformationList= new ArrayList<>();
        try {
            for (TheaterRawInformation theaterRawInformation : source) {
                TheaterInformation theaterInformation = new TheaterInformation();
                theaterInformation.setAddress(theaterRawInformation.getAddress());
                theaterInformation.setCity(theaterRawInformation.getCity());
                theaterInformation.setCountry(theaterRawInformation.getCountry());
                theaterInformation.setExternalTheaterCode(theaterRawInformation.getExternalTheaterCode());
                theaterInformation.setIsMultiplex(theaterRawInformation.getIsMultiplex());
                theaterInformation.setNumberOfScreen(theaterRawInformation.getNumberOfScreen());
                theaterInformation.setPinCode(theaterRawInformation.getPinCode());
                theaterInformation.setState(theaterRawInformation.getState());
                theaterInformation.setTheaterName(theaterRawInformation.getTheaterName());
                String seatClassMap = theaterRawInformation.getSeatClassNames();
                String[] seatClassStr = seatClassMap.split("\\|");
                List<String> seatClassIds = new ArrayList<String>();
                for (String seatClassAndCode : seatClassStr) {
                    String[] splitStr = seatClassAndCode.split(":");
                    SeatClass seatClass = seatClassRepository.findBySeatCode(splitStr[1]);
                    if (seatClass == null) {
                        seatClass = new SeatClass();
                        seatClass.setClassName(splitStr[0]);
                        seatClass.setSeatCode(splitStr[1]);
                        seatClass = seatClassRepository.save(seatClass);
                    }
                    seatClassIds.add(seatClass.getSeatClassId());
                }
                theaterInformation.setSeatClassIds(seatClassIds);
                theaterInformationList.add(theaterInformation);
            }
        }
        catch (Exception e){
            throw  new AnalytiqueException("TheaterInformation transformation failure");
        }
        return theaterInformationList;
    }
}
