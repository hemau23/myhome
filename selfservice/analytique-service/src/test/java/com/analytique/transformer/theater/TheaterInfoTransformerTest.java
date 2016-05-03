package com.analytique.transformer.theater;

import com.analytique.entity.theater.TheaterInformation;
import com.analytique.entity.theater.TheaterRawInformation;
import com.analytique.repository.theater.SeatClassRepository;
import com.analytique.repository.theater.TheaterInformationRepository;
import com.analytique.repository.theater.TheaterRawInformationRepository;
import com.analytique.util.IntegrationFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by hemant on 9/27/2015.
 */
public class TheaterInfoTransformerTest extends IntegrationFlowTest {

    @Autowired
    TheaterRawInformationRepository theaterRawInformationRepository;

    @Autowired
    TheaterInformationRepository theaterInformationRepository;

    @Autowired
    SeatClassRepository seatClassRepository;

    @Autowired
    TheaterInfoTransformer theaterInfoTransformer;

    TheaterRawInformation theaterRawInformation;



    @BeforeMethod
    public void setUp() throws Exception {
        theaterInformationRepository.deleteAll();
        seatClassRepository.deleteAll();
        theaterRawInformationRepository.deleteAll();

        theaterRawInformation = new TheaterRawInformation();
        theaterRawInformation.setState("Maharashtra");
        theaterRawInformation.setCountry("INDIA");
        theaterRawInformation.setCity("PUNE");
        theaterRawInformation.setPinCode(411061);
        theaterRawInformation.setIsMultiplex(true);
        theaterRawInformation.setAddress("ESQuare chowk");
        theaterRawInformation.setNumberOfScreen(4);
        theaterRawInformation.setSeatClassNames("PLATINUM:PLN|GOLD:GLD");
        theaterRawInformation.setTheaterName("ESQuare Cinema");

    }

    @Test
    public void testTransform() throws Exception {
        List<TheaterRawInformation> theaterRawInformationList= new ArrayList<>();
        theaterRawInformationList.add(theaterRawInformation);
        List<TheaterInformation> transform = theaterInfoTransformer.transform(theaterRawInformationList);
        assertEquals(transform.size(),1);
    }
}