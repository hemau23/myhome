package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.movie.Genres;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.exception.AnalytiqueException;
import com.analytique.repository.crew.PersonRepository;
import com.analytique.repository.crew.RoleRepository;
import com.analytique.repository.movie.GenresRepository;
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

    @Autowired
    GenresRepository genresRepository;


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
            String genres = movieRawInformation.getGenres();
            String[] genreNames = genres.split("\\|");
            if (genreNames.length<1){
                throw new AnalytiqueException("Genres not defined");
            }
            List<String> genreIds= new ArrayList<String>();
            for (String genreName :genreNames) {
                Genres alreadyExistGenre = genresRepository.findByGenreName(genreName);
                if (alreadyExistGenre == null){
                    alreadyExistGenre = new Genres();
                    alreadyExistGenre.setGenreName(genreName);
                    alreadyExistGenre= genresRepository.save(alreadyExistGenre);
                }
                genreIds.add(alreadyExistGenre.getGenreId());

            }
            movieInformation.setGenreIds(genreIds);
            movieInformations.add(movieInformation);
        }
        return movieInformations;
    }
}
