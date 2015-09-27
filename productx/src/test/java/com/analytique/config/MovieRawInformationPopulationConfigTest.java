package com.analytique.config;


import com.analytique.repository.bookingdata.MovieRawInformationRepository;
import com.analytique.repository.crew.PersonRepository;
import com.analytique.repository.crew.RoleRepository;
import com.analytique.repository.movie.CastAndCrewRepository;
import com.analytique.repository.movie.GenresRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.util.IntegrationFlowTest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class MovieRawInformationPopulationConfigTest extends IntegrationFlowTest {


    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @Autowired
    GenresRepository genresRepository;

    @Autowired
    CastAndCrewRepository castAndCrewRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MovieRawInformationRepository movieRawInformationRepository;

    private File inputFile;
    private  File archiveDirectory;

    @BeforeMethod
    public void setUp() throws Exception {
        inputFile= new File(this.getClass().getResource("/input/movieInfo.mov").toURI());
        archiveDirectory= new File(propertiesConfig.getIncomingDirectoryPath()+File.separator+"movieInfo.mov");
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
        movieInformationRepository.deleteAll();
        castAndCrewRepository.deleteAll();
        roleRepository.deleteAll();
        genresRepository.deleteAll();
        movieRawInformationRepository.deleteAll();
    }


    @Test
    void testMovieInformationFlow() throws Exception {
        FileUtils.copyFileToDirectory(inputFile, propertiesConfig.getIncomingDirectory());
        waitForFile(archiveDirectory);
        assertEquals(archiveDirectory.exists(),true);
    }
}