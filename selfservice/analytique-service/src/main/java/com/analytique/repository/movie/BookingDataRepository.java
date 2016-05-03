package com.analytique.repository.movie;

import com.analytique.entity.movie.BookingData;
import com.analytique.entity.theater.SeatClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by hemant on 9/10/2015.
 */
@RepositoryRestResource(collectionResourceRel = "bookingData", path = "bookingData")
public interface BookingDataRepository extends MongoRepository<BookingData,String>{

    List<BookingData> findByMovieInformationId(@Param("movieInformationId") String movieInformationId);
    List<BookingData> findByTheaterId(@Param("theaterId") String theaterId);
    List<BookingData> findByMovieInformationIdAndTheaterId(@Param("movieInformationId") String movieInformationId,@Param("theaterId") String theaterId);
    List<BookingData> findByShowDate(@Param("showDate") String showDate);
    List<BookingData> findByShowDateAndTheaterId(@Param("showDate") String showDate,@Param("theaterId") String theaterId);
    List<BookingData> findByShowDateAndMovieInformationId(@Param("showDate") String showDate,@Param("movieInformationId") String movieInformationId);
    List<BookingData> findByShowDateAndMovieInformationIdAndTheaterId(@Param("showDate") String showDate,@Param("movieInformationId") String movieInformationId,@Param("theaterId") String theaterId);
}
