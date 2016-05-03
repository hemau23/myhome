package com.analytique.repository.bookingdata;

import com.analytique.entity.bookingdata.MovieRawInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/22/2015.
 */

@RepositoryRestResource
public interface MovieRawInformationRepository extends MongoRepository<MovieRawInformation,String>{
}
