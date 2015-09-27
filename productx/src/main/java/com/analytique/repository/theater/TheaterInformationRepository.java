package com.analytique.repository.theater;


import com.analytique.entity.theater.TheaterInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by hemant on 9/19/2015.
 */

@RepositoryRestResource
public interface TheaterInformationRepository extends MongoRepository<TheaterInformation,String> {

    TheaterInformation findByExternalTheaterCode(@Param("externalTheaterCode") String externalTheaterCode);
}
