package com.analytique.repository.movie;

import com.analytique.entity.movie.FilmGenres;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/19/2015.
 */
@RepositoryRestResource
public interface FilmGenresRepository extends MongoRepository<FilmGenres,String> {
}
