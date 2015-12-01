package com.analytique.entity.movie;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.Date;


/**
 * Created by hemant on 9/10/2015.
 */
public class BookingData {

    @Id
    String movieBookingId;

    @DelimitedField(name="movieInformationId")
    String movieInformationId;

    @DelimitedField(name="theaterId")
    String theaterId;

    @DelimitedField(name="collectionDate")
    Date dataCollectionDate;

    @DelimitedField(name="showDate")
    String showDate;

    @DelimitedField(name="showTime")
    String showTime;

    @DelimitedField(name="averageRating")
    String averageRating;

    @DelimitedField(name="vote")
    Integer vote;

    @DelimitedField(name="seatClassId")
    String seatClassId;

    @DelimitedField(name="price")
    Integer price;

    @DelimitedField(name="rowName")
    String rowName;

    @DelimitedField(name="capacity")
    Integer capacity;

    @DelimitedField(name="occupied")
    Integer occupied;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

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

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getMovieInformationId() {
        return movieInformationId;
    }

    public void setMovieInformationId(String movieInformationId) {
        this.movieInformationId = movieInformationId;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
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

    public String getSeatClassId() {
        return seatClassId;
    }

    public void setSeatClassId(String seatClassId) {
        this.seatClassId = seatClassId;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOccupied() {
        return occupied;
    }

    public void setOccupied(Integer occupied) {
        this.occupied = occupied;
    }
}
