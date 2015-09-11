package com.analytique.repository.movie;

import com.analytique.entity.movie.MovieInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/10/2015.
 */
@RepositoryRestResource
public interface MovieInformationRepository extends MongoRepository<MovieInformation,String> {
}
