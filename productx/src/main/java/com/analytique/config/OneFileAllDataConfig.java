package com.analytique.config;

import com.analytique.entity.AnalytiqueFileType;
import com.analytique.entity.alldata.OneFileAllData;
import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.movie.CastAndCrew;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.entity.theater.TheaterInformation;
import com.analytique.entity.theater.TheaterRawInformation;
import com.analytique.file.DelimitedFileIterator;
import com.analytique.repository.alldata.OneFileAllDataRepository;
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
 * Created by hemau23 on 11/7/2015.
 */
@Configuration
public class OneFileAllDataConfig {

    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    FileService fileService;

    @Autowired
    OneFileAllDataRepository oneFileAllDataRepository;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    /*@Bean
    public IntegrationFlow movieInformationFlow(){
        return IntegrationFlows.from(Files.inboundAdapter(propertiesConfig.getIncomingDirectory())
                        .autoCreateDirectory(true)
                        .patternFilter("*.tinfo"),
                p -> p.poller(poller()))
                .<File>handle((p, h) -> fileService.moveFileToDirectory(p, propertiesConfig.getArchiveDirectory()))
                .<File, List<OneFileAllData>>transform((s) -> new DelimitedFileIterator<OneFileAllData>(s, AnalytiqueFileType.THEATER_RAW_INFORMATION, OneFileAllData.class).all())
                .<List<OneFileAllData>>handle((p, h) -> oneFileAllDataRepository.save(p))
                .transform(theaterInfoTransformer)
                .<List<TheaterInformation>>handle((p,h) -> theaterInformationRepository.save(p))
                .channel(PropertiesConfig.COMPLETED_MGS_CHANNEL)
                .get();
    }


    @Bean
    public IntegrationFlow movieInformationFlow1(){
        return IntegrationFlows.from(Files.inboundAdapter(propertiesConfig.getIncomingDirectory())
                        .autoCreateDirectory(true)
                        .patternFilter("*.mov"),
                p -> p.poller(poller()))
                .<File>handle((p, h) -> fileService.moveFileToDirectory(p, propertiesConfig.getArchiveDirectory()))
                .<File, List<MovieRawInformation>>transform((s) -> new DelimitedFileIterator<MovieRawInformation>(s, AnalytiqueFileType.MOVIE_RAW_INFORMATION, MovieRawInformation.class).all())
                .<List<MovieRawInformation>>handle((p, h) -> movieRawInformationRepository.save(p))
                .transform(movieInformationTransformer)
                .<List<MovieInformation>>handle((p, h) -> movieInformationRepository.save(p))
                .transform(castAndCrewTransformer)
                .<List<CastAndCrew>>handle((p,h) -> castAndCrewRepository.save(p))
                .channel(PropertiesConfig.COMPLETED_MGS_CHANNEL)
                .get();
    }*/
}
