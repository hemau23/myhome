package com.analytique.entity.movie;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by hemant on 9/10/2015.
 */
public class MovieBookingData {

    @Id
    private String movieId;

    @DelimitedField(name="dataCollectionDate")
    Date dataCollectionDate;

    @DelimitedField(name="dataCollectionTime")
    Timestamp dataCollectionTime;

    @DelimitedField(name="showDate")
    Date showDate;

    @DelimitedField(name="showTime")
    Timestamp showTime;

    @DelimitedField(name="movieName")
    String movieName;

    @DelimitedField(name="theaterCode")
    String theaterCode;

    @DelimitedField(name="theaterName")
    String theaterName;

    @DelimitedField(name="averageRating")
    String averageRating;

    @DelimitedField(name="duration")
    Integer duration;

    @DelimitedField(name="vote")
    Integer vote;

    @DelimitedField(name="certificate")
    String certificate;

    @DelimitedField(name="type")
    String type;

    @DelimitedField(name="directorName")
    String directorName;

    @DelimitedField(name="cast")
    String cast;

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

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Date getDataCollectionDate() {
        return dataCollectionDate;
    }

    public void setDataCollectionDate(Date dataCollectionDate) {
        this.dataCollectionDate = dataCollectionDate;
    }

    public Timestamp getDataCollectionTime() {
        return dataCollectionTime;
    }

    public void setDataCollectionTime(Timestamp dataCollectionTime) {
        this.dataCollectionTime = dataCollectionTime;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public Timestamp getShowTime() {
        return showTime;
    }

    public void setShowTime(Timestamp showTime) {
        this.showTime = showTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheaterCode() {
        return theaterCode;
    }

    public void setTheaterCode(String theaterCode) {
        this.theaterCode = theaterCode;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
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
