package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.MovieRawInformation;
import com.analytique.entity.movie.Genres;
import com.analytique.entity.movie.MovieInformation;
import com.analytique.exception.AnalytiqueException;
import com.analytique.repository.movie.GenresRepository;
import com.analytique.repository.movie.MovieInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemant on 9/21/2015.
 */
@Component
public class MovieInformationTransformer implements GenericTransformer<List<MovieRawInformation>,List<MovieInformation>> {

    final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    MovieInformationRepository movieInformationRepository;

    @Autowired
    GenresRepository genresRepository;


    @Override
    public List<MovieInformation> transform(List<MovieRawInformation> source) {

        List<MovieInformation> movieInformations= new ArrayList<>();
        for (MovieRawInformation movieRawInformation :source){
            MovieInformation movieInformation = movieInformationRepository.findByMovieExternalCode(movieRawInformation.getMovieExternalCode());
            if (movieInformation == null) {
                movieInformation = new MovieInformation();
                movieInformation.setMovieExternalCode(movieRawInformation.getMovieExternalCode());
                movieInformation.setMovieName(movieRawInformation.getMovieName());
            }
            movieInformation.setCertificate(movieRawInformation.getCertificate());
            movieInformation.setReleaseDate(formatter.format(movieRawInformation.getReleaseDate()));
            movieInformation.setIsHitSongs(movieRawInformation.isHitSongs());
            movieInformation.setDuration(movieRawInformation.getDuration());
            movieInformation.setLanguage(movieRawInformation.getLanguage());
            movieInformation.setRating(movieRawInformation.getRating());
            movieInformation.setTrailerUrl(movieRawInformation.getTrailerUrl());
            movieInformation.setFShareURL(movieRawInformation.getFShareURL());

            StringBuffer crew= new StringBuffer();
            crew.append("ACTOR:").append(movieRawInformation.getActor())
                    .append("|DIRECTOR:").append(movieRawInformation.getDirector())
                    .append("|MUSIC DIRECTOR:").append(movieRawInformation.getMusicDirector());

            movieInformation.setCrew(crew.toString());
            String genres = movieRawInformation.getGenres();
            String[] genreNames = genres.split(",");
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
                if (genreIds.size()>1) break;

            }
            movieInformation.setGenreId1(genreIds.get(0));
            if (genreIds.size()>1)
            movieInformation.setGenreId2(genreIds.get(1));
            movieInformations.add(movieInformation);
        }
        return movieInformations;
    }
}
