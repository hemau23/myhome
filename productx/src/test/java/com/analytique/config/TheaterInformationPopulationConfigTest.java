package com.analytique.config;


import com.analytique.repository.theater.SeatClassRepository;
import com.analytique.repository.theater.TheaterInformationRepository;
import com.analytique.repository.theater.TheaterRawInformationRepository;
import com.analytique.util.IntegrationFlowTest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

/**
 * Created by hemant on 9/26/2015.
 */

@Test(groups = { "Analytique", "config1" })
@ContextConfiguration(classes = {TestApplicationConfig.class})
public class TheaterInformationPopulationConfigTest extends IntegrationFlowTest{

    @Autowired
    TheaterInformationRepository theaterInformationRepository;

    @Autowired
    TheaterRawInformationRepository theaterRawInformationRepository;

    @Autowired
    SeatClassRepository seatClassRepository;

    @Autowired
    PropertiesConfig propertiesConfig;


    private File inputFile;
    private  File archiveDirectory;

    @BeforeMethod
    public void setUp() throws Exception {
        inputFile= new File(this.getClass().getResource("/input/theaterInfo.tinfo").toURI());
        archiveDirectory= new File(propertiesConfig.getIncomingDirectoryPath()+File.separator+"theaterInfo.tinfo");
        cleanup();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    private void cleanup() {
        if (archiveDirectory != null
                & archiveDirectory.exists()) {
            archiveDirectory.delete();
        }
        theaterInformationRepository.deleteAll();
        theaterRawInformationRepository.deleteAll();
    }

    @Test
    public void testMovieInformationFlow() throws Exception {
        FileUtils.copyFileToDirectory(inputFile, propertiesConfig.getIncomingDirectory());
        waitForFile(archiveDirectory);
        assertEquals(archiveDirectory.exists(),true);
    }
}