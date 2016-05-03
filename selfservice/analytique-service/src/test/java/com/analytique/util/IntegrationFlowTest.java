package com.analytique.util;

import com.analytique.config.TestApplicationConfig;
import com.analytique.repository.monitor.CompletedMessageRepository;
import com.analytique.repository.monitor.ErrorMesssageRepository;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

@WebAppConfiguration
@ContextConfiguration(classes = { TestApplicationConfig.class })
public abstract class IntegrationFlowTest extends AbstractTestNGSpringContextTests {


    @Autowired
    CompletedMessageRepository completedMessageRepository;

    @Autowired
    ErrorMesssageRepository errorMesssageRepository;

    protected static final int MILLIS_BETWEEN_RETRIES = 200;
    protected static final int TIMEOUT_MILLIS = 30000;

    @BeforeMethod
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void after() {
        completedMessageRepository.deleteAll();
        errorMesssageRepository.deleteAll();
    }

    public void waitForFile(File file) {
        // Keep track of the elapsed time
        long elapsedMillis = 0;
        long startMillis = System.currentTimeMillis();

        while (!file.exists()) {
            // Calcuate the number of milliseconds since the start of the wait
            elapsedMillis = System.currentTimeMillis() - startMillis;

            // If we have waited for longer than the expected amount of time, blow up so we don't hang out infinitely
            if (elapsedMillis > TIMEOUT_MILLIS) {
                throw new RuntimeException("Exceeded timeout waiting for file at: " + file + " messages");
            }

            // Sleep for some milliseconds before re-checking
            try {
                Thread.sleep(MILLIS_BETWEEN_RETRIES);
            } catch (InterruptedException e) {
                throw new RuntimeException("InterruptedException happened while attempting to waiting for file", e);
            }
        }
    }
}
