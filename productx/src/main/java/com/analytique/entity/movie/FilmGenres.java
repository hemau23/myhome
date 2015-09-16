package com.analytique.entity.movie;

import org.springframework.data.annotation.Id;

public class FilmGenres {

    @Id
    Long filmGenresId;

    Long genreId;
    Long movieId;

    public Long getFilmGenresId() {
        return filmGenresId;
    }

    public void setFilmGenresId(Long filmGenresId) {
        this.filmGenresId = filmGenresId;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
