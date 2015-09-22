package com.analytique.entity.bookingdata;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hemant on 9/21/2015.
 */
public class MovieRawInformation {

    @Id
    String movieRawId;

    @DelimitedField(name="movieName")
    String movieName;

    @DelimitedField(name="duration")
    Integer duration;

    @DelimitedField(name="certificate")
    String certificate;

    @DelimitedField(name="isHitSongs")
    boolean isHitSongs;

    @DelimitedField(name="releaseDate")
    Date releaseDate;

    @DelimitedField(name="crew")
    Map<String,String> crew;

    @DelimitedField(name = "genres")
    List<String> genres;

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

    public Map<String, String> getCrew() {
        return crew;
    }

    public void setCrew(Map<String, String> crew) {
        this.crew = crew;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
