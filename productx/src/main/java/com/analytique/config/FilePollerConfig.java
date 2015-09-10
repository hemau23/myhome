package com.analytique.config;

import com.analytique.entity.AnalytiqueFileType;
import com.analytique.entity.Customer;
import com.analytique.file.DelimitedFileIterator;
import com.analytique.repository.CustomerRepository;
import com.analytique.util.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;

import org.springframework.integration.dsl.file.Files;

import org.springframework.integration.scheduling.PollerMetadata;

import java.io.File;
import java.util.List;


@Configuration
public class FilePollerConfig {


    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    CustomerRepository customerRepository;


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
                .channel(PropertiesConfig.COMPLETED_MGS_CHANNEL)
                .get();
    }

}
