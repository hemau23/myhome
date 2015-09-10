package com.analytique.config;

import com.analytique.entity.AnalytiqueFileType;
import com.analytique.entity.Customer;
import com.analytique.entity.ErrorMessage;
import com.analytique.file.DelimitedFileIterator;
import com.analytique.repository.CustomerRepository;
import com.analytique.repository.ErrorMesssageRepository;
import com.analytique.transformer.ErrorMessageTransformer;
import com.analytique.transformer.FileTransformer;
import com.analytique.util.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;

import org.springframework.integration.dsl.file.Files;

import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.util.List;

/**
 * Created by hemant on 9/7/2015.
 */

@Configuration
public class FilePollerConfig {


    private static final String STAR_MONTHLY_FILE_INPROCESS_CHANNEL = "saveDataChannel";
    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    FileTransformer fileTransformer;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ErrorMessageTransformer errorMessageTransformer;

    @Autowired
    ErrorMesssageRepository errorMesssageRepository;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    @Bean
    public IntegrationFlow rawDataPopulatin() {
        return IntegrationFlows.from(Files.inboundAdapter(new File(propertiesConfig.getIncomingDirectory()))
                        .autoCreateDirectory(true)
                        .patternFilter("*.*"),
                 p -> p.poller(poller()))
                .<File,List<Customer>>transform((s) -> new DelimitedFileIterator<Customer>(s, AnalytiqueFileType.CUSTOMER, Customer.class).all())
                .enrichHeaders(MapBuilder.with("Status", "Running").get())
                .<List<Customer>>handle((p, h) -> customerRepository.save(p))
                .enrichHeaders(MapBuilder.with("Status", "Complete").get())
                .channel("nullChannel")
                .get();
    }

    @Bean
    public IntegrationFlow errorChannelFlow() {
        return IntegrationFlows
                .from(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME)
                .transform(errorMessageTransformer)
                .<ErrorMessage>handle((p,h) -> errorMesssageRepository.save(p))
                .channel("nullChannel")
                .get();
    }



}
