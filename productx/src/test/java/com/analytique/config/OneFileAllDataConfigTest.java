package com.analytique.config;

import com.analytique.util.FileService;
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
 * Created by hemau23 on 11/7/2015.
 */

@Test(groups = { "Analytique1", "config1" })
@ContextConfiguration(classes = {TestApplicationConfig.class})
public class OneFileAllDataConfigTest extends IntegrationFlowTest {

    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    FileService fileService;

    private File inputFile;
    private  File archiveDirectory;

    @BeforeMethod
    public void setUp() throws Exception {
        inputFile= new File(this.getClass().getResource("/input/mydata-20151028.txt").toURI());
        archiveDirectory= new File(propertiesConfig.getIncomingDirectoryPath()+File.separator+"mydata-20151028.txt");
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

    }

    @Test
    public void testMovieInformationFlow() throws Exception {
        FileUtils.copyFileToDirectory(inputFile, propertiesConfig.getIncomingDirectory());
        waitForFile(archiveDirectory);
        assertEquals(archiveDirectory.exists(),true);
    }

}