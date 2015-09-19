package com.analytique.repository.theater;

import com.analytique.entity.movie.BookingData;
import com.analytique.entity.theater.TheaterInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 * Created by hemant on 9/19/2015.
 */

@RepositoryRestController
public interface TheaterInformationRepository extends MongoRepository<TheaterInformation,String> {
}
