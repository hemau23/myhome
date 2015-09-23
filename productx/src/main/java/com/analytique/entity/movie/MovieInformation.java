package com.analytique.entity.movie;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by hemant on 9/10/2015.
 */
public class MovieInformation {

    @Id
    String movieInformationId;

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
    String crew;

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieInformationId() {
        return movieInformationId;
    }

    public void setMovieInformationId(String movieInformationId) {
        this.movieInformationId = movieInformationId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public boolean isHitSongs() {
        return isHitSongs;
    }

    public void setIsHitSongs(boolean isHitSongs) {
        this.isHitSongs = isHitSongs;
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

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }
}
