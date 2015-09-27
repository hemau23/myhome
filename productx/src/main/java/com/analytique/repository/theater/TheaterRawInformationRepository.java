package com.analytique.repository.theater;

import com.analytique.entity.theater.TheaterRawInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/27/2015.
 */
@RepositoryRestResource
public interface TheaterRawInformationRepository extends MongoRepository<TheaterRawInformation,String> {
}
