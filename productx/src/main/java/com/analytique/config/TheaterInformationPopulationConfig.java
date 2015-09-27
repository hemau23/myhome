package com.analytique.config;

import com.analytique.entity.AnalytiqueFileType;
import com.analytique.entity.theater.TheaterInformation;
import com.analytique.entity.theater.TheaterRawInformation;
import com.analytique.file.DelimitedFileIterator;
import com.analytique.repository.theater.TheaterInformationRepository;
import com.analytique.repository.theater.TheaterRawInformationRepository;
import com.analytique.transformer.theater.TheaterInfoTransformer;
import com.analytique.util.FileService;
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

/**
 * Created by hemant on 9/26/2015.
 */

@Configuration
public class TheaterInformationPopulationConfig {

    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    FileService fileService;

    @Autowired
    TheaterInformationRepository theaterInformationRepository;

    @Autowired
    TheaterRawInformationRepository theaterRawInformationRepository;

    @Autowired
    TheaterInfoTransformer theaterInfoTransformer;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    @Bean
    public IntegrationFlow movieInformationFlow(){
        return IntegrationFlows.from(Files.inboundAdapter(propertiesConfig.getIncomingDirectory())
                        .autoCreateDirectory(true)
                        .patternFilter("*.mov"),
                p -> p.poller(poller()))
                .<File>handle((p, h) -> fileService.moveFileToDirectory(p, propertiesConfig.getArchiveDirectory()))
                .<File, List<TheaterRawInformation>>transform((s) -> new DelimitedFileIterator<TheaterRawInformation>(s, AnalytiqueFileType.THEATER_RAW_INFORMATION, TheaterRawInformation.class).all())
                .<List<TheaterRawInformation>>handle((p, h) -> theaterRawInformationRepository.save(p))
                .transform(theaterInfoTransformer)
                .<List<TheaterInformation>>handle((p,h) -> theaterInformationRepository.save(p))
                .channel(PropertiesConfig.COMPLETED_MGS_CHANNEL)
                .get();
    }
}
