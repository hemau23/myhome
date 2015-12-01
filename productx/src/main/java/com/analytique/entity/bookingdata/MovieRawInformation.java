package com.analytique.entity.bookingdata;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by hemant on 9/21/2015.
 */
public class MovieRawInformation {

    @Id
    String movieRawId;

    @DelimitedField(name="movieName")
    String movieName;

    @DelimitedField(name="Length")
    Integer duration;

    @DelimitedField(name="EventCensor")
    String certificate;

    @DelimitedField(name="isHitSongs")
    boolean isHitSongs;

    @DelimitedField(name="EventReleaseDate")
    Date releaseDate;

    @DelimitedField(name="Director")
    String director;


    @DelimitedField(name="Actors")
    String actor;

    @DelimitedField(name = "Genre")
    String genres;

    @DelimitedField(name = "EventCode")
    String movieExternalCode;

    @DelimitedField(name="Ratings")
    String rating;

    @DelimitedField(name="Language")
    String language;

    @DelimitedField(name="TrailerURL")
    String trailerUrl;


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getMovieExternalCode() {
        return movieExternalCode;
    }

    public void setMovieExternalCode(String movieExternalCode) {
        this.movieExternalCode = movieExternalCode;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getMovieRawId() {
        return movieRawId;
    }

    public void setMovieRawId(String movieRawId) {
        this.movieRawId = movieRawId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public boolean isHitSongs() {
        return isHitSongs;
    }

    public void setIsHitSongs(boolean isHitSongs) {
        this.isHitSongs = isHitSongs;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
