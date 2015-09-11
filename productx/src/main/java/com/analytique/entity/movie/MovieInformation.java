package com.analytique.entity.movie;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

/**
 * Created by hemant on 9/10/2015.
 */
public class MovieInformation {

    @Id
    private Long movieId;

    @DelimitedField(name="movieName")
    String movieName;

    @DelimitedField(name="cast")
    String cast;

    @DelimitedField(name="duration")
    String duration;

    @DelimitedField(name="certificate")
    String certificate;

    @DelimitedField(name="director")
    String director;

    @DelimitedField(name="type")
    String type;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
