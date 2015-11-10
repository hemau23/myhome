package com.analytique.config;

import com.analytique.entity.AnalytiqueFileType;
import com.analytique.entity.alldata.OneFileAllData;
import com.analytique.entity.bookingdata.BookingRawData;
import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.movie.BookingData;
import com.analytique.entity.movie.CastAndCrew;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.entity.theater.TheaterInformation;
import com.analytique.entity.theater.TheaterRawInformation;
import com.analytique.file.DelimitedFileIterator;
import com.analytique.repository.alldata.OneFileAllDataRepository;
import com.analytique.repository.movie.BookingDataRepository;
import com.analytique.transformer.alldata.OneFileAllDataTransformer;
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

public class OneFileAllDataConfig {

    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    FileService fileService;

    @Autowired
    OneFileAllDataRepository oneFileAllDataRepository;


    @Autowired
    OneFileAllDataTransformer oneFileAllDataTransformer;

    @Autowired
    BookingDataRepository bookingDataRepository;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    @Bean
    public IntegrationFlow movieInformationFlow(){
        return IntegrationFlows.from(Files.inboundAdapter(propertiesConfig.getIncomingDirectory())
                        .autoCreateDirectory(true)
                        .patternFilter("mydata-20151029.txt"),
                p -> p.poller(poller()))
                .<File>handle((p, h) -> fileService.moveFileToDirectory(p, propertiesConfig.getArchiveDirectory()))
                .<File, List<OneFileAllData>>transform((s) -> new DelimitedFileIterator<OneFileAllData>(s, AnalytiqueFileType.THEATER_RAW_INFORMATION, OneFileAllData.class).all())
                .<List<OneFileAllData>>handle((p, h) -> oneFileAllDataRepository.save(p))
                .transform(oneFileAllDataTransformer)
                .<List<BookingData>>handle((p,h) -> bookingDataRepository.save(p))
                .channel(PropertiesConfig.COMPLETED_MGS_CHANNEL)
                .get();
    }

}
