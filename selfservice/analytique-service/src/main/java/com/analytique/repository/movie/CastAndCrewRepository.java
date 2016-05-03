package com.analytique.repository.movie;

import com.analytique.entity.movie.BookingData;
import com.analytique.entity.movie.CastAndCrew;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by hemant on 9/19/2015.
 */
@RepositoryRestResource(collectionResourceRel = "castAndCrew", path = "castAndCrew")
public interface CastAndCrewRepository extends MongoRepository<CastAndCrew,String> {

    List<CastAndCrew> findByMovieInformationId(@Param("movieInformationId") String movieInformationId);
}
