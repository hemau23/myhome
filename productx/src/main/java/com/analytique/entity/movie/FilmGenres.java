package com.analytique.entity.movie;

import org.springframework.data.annotation.Id;

public class FilmGenres {

    @Id
    String filmGenresId;

    String genreId;
    String movieId;

    public String getFilmGenresId() {
        return filmGenresId;
    }

    public void setFilmGenresId(String filmGenresId) {
        this.filmGenresId = filmGenresId;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
