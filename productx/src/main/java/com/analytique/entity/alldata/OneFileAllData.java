package com.analytique.entity.alldata;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;


/**
 * Created by hemau23 on 11/7/2015.
 */
public class OneFileAllData {

    @Id
    String Id;

    @DelimitedField(name="externalTheaterCode")
    String externalTheaterCode;

    @DelimitedField(name="movieName")
    String movieName;

    @DelimitedField(name="showDateTime")
    String showDateTime;

    @DelimitedField(name="movieExternalCode")
    String movieExternalCode;

    @DelimitedField(name="showUniqueId")
    String showUniqueId;

    @DelimitedField(name="seatMap")
    String seatMap;

    @DelimitedField(name="percentage")
    String percentage;

    @DelimitedField(name="votes")
    String votes;

    @DelimitedField(name="criticsRatings")
    String criticsRatings;

    @DelimitedField(name="userRatings")
    String userRatings;

    @DelimitedField(name="youTubeHits")
    String youTubeHits;

    @DelimitedField(name="tomatoesRatings")
    String tomatoesRatings;

    @DelimitedField(name="tomatoUserReviewsCount")
    String tomatoUserReviewsCount;

    @DelimitedField(name="imdbRatings")
    String imdbRatings;

    @DelimitedField(name="imdbVotesCount")
    String imdbVotesCount;

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getCriticsRatings() {
        return criticsRatings;
    }

    public void setCriticsRatings(String criticsRatings) {
        this.criticsRatings = criticsRatings;
    }

    public String getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(String userRatings) {
        this.userRatings = userRatings;
    }

    public String getYouTubeHits() {
        return youTubeHits;
    }

    public void setYouTubeHits(String youTubeHits) {
        this.youTubeHits = youTubeHits;
    }

    public String getTomatoesRatings() {
        return tomatoesRatings;
    }

    public void setTomatoesRatings(String tomatoesRatings) {
        this.tomatoesRatings = tomatoesRatings;
    }

    public String getTomatoUserReviewsCount() {
        return tomatoUserReviewsCount;
    }

    public void setTomatoUserReviewsCount(String tomatoUserReviewsCount) {
        this.tomatoUserReviewsCount = tomatoUserReviewsCount;
    }

    public String getImdbRatings() {
        return imdbRatings;
    }

    public void setImdbRatings(String imdbRatings) {
        this.imdbRatings = imdbRatings;
    }

    public String getImdbVotesCount() {
        return imdbVotesCount;
    }

    public void setImdbVotesCount(String imdbVotesCount) {
        this.imdbVotesCount = imdbVotesCount;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getExternalTheaterCode() {
        return externalTheaterCode;
    }

    public void setExternalTheaterCode(String externalTheaterCode) {
        this.externalTheaterCode = externalTheaterCode;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(String showDateTime) {
        this.showDateTime = showDateTime;
    }

    public String getMovieExternalCode() {
        return movieExternalCode;
    }

    public void setMovieExternalCode(String movieExternalCode) {
        this.movieExternalCode = movieExternalCode;
    }

    public String getShowUniqueId() {
        return showUniqueId;
    }

    public void setShowUniqueId(String showUniqueId) {
        this.showUniqueId = showUniqueId;
    }

    public String getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(String seatMap) {
        this.seatMap = seatMap;
    }
}
