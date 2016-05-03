package com.analytique.repository.movie;

import com.analytique.entity.movie.Genres;
import com.analytique.entity.movie.MovieInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/19/2015.
 */
@RepositoryRestResource(collectionResourceRel = "genres", path = "genres")
public interface GenresRepository extends MongoRepository<Genres,String> {

    Genres findByGenreName(@Param("genreName") String genreName);


}
