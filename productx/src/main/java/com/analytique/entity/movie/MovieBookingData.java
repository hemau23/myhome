package com.analytique.entity.movie;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.Date;


/**
 * Created by hemant on 9/10/2015.
 */
public class MovieBookingData {

    @Id
    private String movieBookingId;

    @DelimitedField(name="movieInformationId")
    Long movieInformationId;

    @DelimitedField(name="theaterId")
    Integer theaterId;

    @DelimitedField(name="dataCollectionDate")
    Date dataCollectionDate;

    @DelimitedField(name="dataCollectionTime")
    String dataCollectionTime;

    @DelimitedField(name="showDate")
    Date showDate;

    @DelimitedField(name="showTime")
    String showTime;

    @DelimitedField(name="averageRating")
    String averageRating;

    @DelimitedField(name="vote")
    Integer vote;

    @DelimitedField(name="seatClassId")
    Integer seatClassId;

    @DelimitedField(name="price")
    Integer price;

    @DelimitedField(name="seatRowCode")
    String seatRowCode;

    @DelimitedField(name="seat")
    String seat;

    public String getMovieBookingId() {
        return movieBookingId;
    }

    public void setMovieBookingId(String movieBookingId) {
        this.movieBookingId = movieBookingId;
    }

    public Date getDataCollectionDate() {
        return dataCollectionDate;
    }

    public void setDataCollectionDate(Date dataCollectionDate) {
        this.dataCollectionDate = dataCollectionDate;
    }

    public String getDataCollectionTime() {
        return dataCollectionTime;
    }

    public void setDataCollectionTime(String dataCollectionTime) {
        this.dataCollectionTime = dataCollectionTime;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Long getMovieInformationId() {
        return movieInformationId;
    }

    public void setMovieInformationId(Long movieInformationId) {
        this.movieInformationId = movieInformationId;
    }

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSeatClassId() {
        return seatClassId;
    }

    public void setSeatClassId(Integer seatClassId) {
        this.seatClassId = seatClassId;
    }

    public String getSeatRowCode() {
        return seatRowCode;
    }

    public void setSeatRowCode(String seatRowCode) {
        this.seatRowCode = seatRowCode;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
