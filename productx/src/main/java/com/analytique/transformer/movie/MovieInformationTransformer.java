package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.repository.crew.PersonRepository;
import com.analytique.repository.crew.RoleRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemant on 9/21/2015.
 */
@Component
public class MovieInformationTransformer implements GenericTransformer<List<MovieRawInformation>,List<MovieInformation>> {

    @Autowired
    MovieInformationRepository movieInformationRepository;


    @Override
    public List<MovieInformation> transform(List<MovieRawInformation> source) {


        List<MovieInformation> movieInformations= new ArrayList<>();
        for (MovieRawInformation movieRawInformation :source){
            MovieInformation movieInformation = movieInformationRepository.findByMovieName(movieRawInformation.getMovieName());
            if (movieInformation == null) {
                movieInformation = new MovieInformation();
            }
            movieInformation.setCertificate(movieRawInformation.getCertificate());
            movieInformation.setReleaseDate(movieRawInformation.getReleaseDate());
            movieInformation.setMovieName(movieRawInformation.getMovieName());
            movieInformation.setIsHitSongs(movieRawInformation.isHitSongs());
            movieInformation.setDuration(movieRawInformation.getDuration());
            movieInformation.setCrew(movieRawInformation.getCrew());
            movieInformations.add(movieInformation);
        }
        return movieInformations;
    }




}
