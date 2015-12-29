package com.analytique.entity.bookingdata;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class BookingRawData {

    @Id
    String Id;

    @DelimitedField(name="movieInformationId")
    String movieInformationId;

    @DelimitedField(name="collectionDate")
    Date dataCollectionDate;

    @DelimitedField(name="showDate")
    String showDate;

    @DelimitedField(name="showTime")
    Date showTime;

    @DelimitedField(name="averageRating")
    String averageRating;



    @DelimitedField(name="price")
    Integer price;

    @DelimitedField(name="rowName")
    String rowName;

    @DelimitedField(name="seatMap")
    String seatMap;

    @DelimitedField(name="className")
    String className;

    @DelimitedField(name="seatCode")
    String seatCode;

    @DelimitedField(name="externalTheaterCode")
    String externalTheaterCode;

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

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMovieInformationId() {
        return movieInformationId;
    }

    public void setMovieInformationId(String movieInformationId) {
        this.movieInformationId = movieInformationId;
    }

    public Date getDataCollectionDate() {
        return dataCollectionDate;
    }

    public void setDataCollectionDate(Date dataCollectionDate) {
        this.dataCollectionDate = dataCollectionDate;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(String seatMap) {
        this.seatMap = seatMap;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getExternalTheaterCode() {
        return externalTheaterCode;
    }

    public void setExternalTheaterCode(String externalTheaterCode) {
        this.externalTheaterCode = externalTheaterCode;
    }
}
