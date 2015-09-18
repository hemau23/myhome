package com.analytique.config;

import com.analytique.repository.bookingdata.BookingRawDataRepository;
import com.analytique.util.IntegrationFlowTest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.*;

@Test(groups = { "Analytique", "config" })
@ContextConfiguration(classes = {TestApplicationConfig.class})
public class FilePollerConfigTest extends IntegrationFlowTest {


    @Autowired
    BookingRawDataRepository bookingRawDataRepository;

    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    FilePollerConfig filePollerConfig;

    private File inputFile;
    private  File archiveDirectory;

    @BeforeMethod
    public void setUp() throws Exception {
        inputFile= new File(this.getClass().getResource("/input/input_1.csv").toURI());
        archiveDirectory= new File(propertiesConfig.getIncomingDirectoryPath()+File.separator+"input_1.csv");
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
        bookingRawDataRepository.deleteAll();
    }

    @Test
    void testRawDataPopulation() throws IOException {
        FileUtils.copyFileToDirectory(inputFile,propertiesConfig.getIncomingDirectory());
        waitForFile(archiveDirectory);
        assertEquals(archiveDirectory.exists(),true);
    }
}