package com.analytique.repository.theater;

import com.analytique.entity.theater.SeatClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/19/2015.
 */
@RepositoryRestResource
public interface SeatClassRepository extends MongoRepository<SeatClass,String> {
}
