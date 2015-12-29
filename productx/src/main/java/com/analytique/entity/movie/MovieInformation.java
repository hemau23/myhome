package com.analytique.entity.movie;

import org.springframework.data.annotation.Id;

/**
 * Created by hemant on 9/10/2015.
 */
public class MovieInformation {

    @Id
    String movieInformationId;

    String movieName;
    String duration;
    String certificate;
    boolean isHitSongs;
    String releaseDate;
    String genreId1;
    String genreId2;
    String movieExternalCode;
    String rating;
    String language;
    String trailerUrl;
    String crew;
    String fShareURL;

    public String getFShareURL() {
        return fShareURL;
    }

    public void setFShareURL(String fShareURL) {
        this.fShareURL = fShareURL;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
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

    public String getMovieExternalCode() {
        return movieExternalCode;
    }

    public void setMovieExternalCode(String movieExternalCode) {
        this.movieExternalCode = movieExternalCode;
    }


    public String getMovieInformationId() {
        return this.movieInformationId;
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


    public String getGenreId1() {
        return genreId1;
    }

    public void setGenreId1(String genreId1) {
        this.genreId1 = genreId1;
    }

    public String getGenreId2() {
        return genreId2;
    }

    public void setGenreId2(String genreId2) {
        this.genreId2 = genreId2;
    }
}
