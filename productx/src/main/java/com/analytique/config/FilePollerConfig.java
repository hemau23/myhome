package com.analytique.config;

import com.analytique.entity.AnalytiqueFileType;
import com.analytique.entity.bookingdata.BookingRawData;
import com.analytique.entity.movie.BookingData;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.file.DelimitedFileIterator;
import com.analytique.repository.bookingdata.BookingRawDataRepository;
import com.analytique.repository.movie.BookingDataRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import com.analytique.transformer.movie.BookingDataTransformer;
import com.analytique.util.FileService;
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
    BookingRawDataRepository bookingRawDataRepository;

    @Autowired
    BookingDataRepository bookingDataRepository;

    @Autowired
    BookingDataTransformer bookingDataTransformer;

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @Autowired
    FileService fileService;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    @Bean
    public IntegrationFlow rawDataPopulation() {
        return IntegrationFlows.from(Files.inboundAdapter(propertiesConfig.getIncomingDirectory())
                        .autoCreateDirectory(true)
                        .patternFilter("*.brd"),
                p -> p.poller(poller()))
                .<File>handle((p,h) -> fileService.moveFileToDirectory(p,propertiesConfig.getArchiveDirectory()))
                .<File, List<BookingRawData>>transform((s) -> new DelimitedFileIterator<BookingRawData>(s, AnalytiqueFileType.BOOKING_RAW_DATA, BookingRawData.class).all())
                .enrichHeaders(MapBuilder.with("Status", "Running").get())
                .<List<BookingRawData>>handle((p, h) -> bookingRawDataRepository.save(p))
                .<List<BookingRawData>,List<BookingData>>transform(bookingDataTransformer)
                .<List<BookingData>>handle((p,h) -> bookingDataRepository.save(p))
                .channel(PropertiesConfig.COMPLETED_MGS_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow movieInformationFlow(){
        return IntegrationFlows.from(Files.inboundAdapter(propertiesConfig.getIncomingDirectory())
                        .autoCreateDirectory(true)
                        .patternFilter("*.mov"),
                p -> p.poller(poller()))
                .<File>handle((p, h) -> fileService.moveFileToDirectory(p, propertiesConfig.getArchiveDirectory()))
                .<File, List<MovieInformation>>transform((s) -> new DelimitedFileIterator<MovieInformation>(s, AnalytiqueFileType.MOVIE_INFORMATION, MovieInformation.class).all())
                .<List<MovieInformation>>handle((p, h) -> movieInformationRepository.save(p))



    }




}
