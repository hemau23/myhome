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

@WebAppConfiguration
@ContextConfiguration(classes = { TestApplicationConfig.class })
public abstract class IntegrationFlowTest extends AbstractTestNGSpringContextTests {


    @Autowired
    CompletedMessageRepository completedMessageRepository;

    @Autowired
    ErrorMesssageRepository errorMesssageRepository;


    @BeforeMethod
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void after() {
        completedMessageRepository.deleteAll();
        errorMesssageRepository.deleteAll();
    }
}
