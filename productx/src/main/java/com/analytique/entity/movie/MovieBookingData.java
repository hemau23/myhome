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

    @DelimitedField(name="dataCollectionDate")
    Date dataCollectionDate;

    @DelimitedField(name="dataCollectionTime")
    String dataCollectionTime;

    @DelimitedField(name="showDate")
    Date showDate;

    @DelimitedField(name="showTime")
    String showTime;

    @DelimitedField(name="movieInformationId")
    Long movieInformationId;

    @DelimitedField(name="externalTheaterCode")
    String externalTheaterCode;

    @DelimitedField(name="averageRating")
    String averageRating;

    @DelimitedField(name="vote")
    Integer vote;

    @DelimitedField(name="seatClassName")
    String seatClassName;

    @DelimitedField(name="seatClassCode")
    String seatClassCode;

    @DelimitedField(name="price")
    Integer price;

    @DelimitedField(name="seatClassRowCode")
    String seatClassRowCode;

    @DelimitedField(name="number")
    String number;

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

    public String getExternalTheaterCode() {
        return externalTheaterCode;
    }

    public void setExternalTheaterCode(String externalTheaterCode) {
        this.externalTheaterCode = externalTheaterCode;
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

    public String getSeatClassName() {
        return seatClassName;
    }

    public void setSeatClassName(String seatClassName) {
        this.seatClassName = seatClassName;
    }

    public String getSeatClassCode() {
        return seatClassCode;
    }

    public void setSeatClassCode(String seatClassCode) {
        this.seatClassCode = seatClassCode;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSeatClassRowCode() {
        return seatClassRowCode;
    }

    public void setSeatClassRowCode(String seatClassRowCode) {
        this.seatClassRowCode = seatClassRowCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
