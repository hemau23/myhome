package com.analytique.repository.movie;

import com.analytique.entity.movie.MovieInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * Created by hemant on 9/10/2015.
 */
@RepositoryRestResource(collectionResourceRel = "movieInformation", path = "movieInformation")
public interface MovieInformationRepository extends MongoRepository<MovieInformation,String> {

    MovieInformation findByMovieName(@Param("movieName") String movieName);

    @Query(value="{}", fields="{ 'movieExternalCode' : 1}")
    List<MovieInformation> findByCertificateNull();

    MovieInformation findByMovieExternalCode(@Param("movieExternalCode") String movieExternalCode);



}
