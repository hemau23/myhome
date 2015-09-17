package com.analytique.entity.movie;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.Date;


/**
 * Created by hemant on 9/10/2015.
 */
public class BookingData {

    @Id
    private String movieBookingId;

    @DelimitedField(name="movieInformationId")
    Long movieInformationId;

    @DelimitedField(name="theaterId")
    Integer theaterId;

    @DelimitedField(name="collectionDate")
    Date dataCollectionDate;

    @DelimitedField(name="showDateTime")
    Date showDate;

    @DelimitedField(name="averageRating")
    String averageRating;

    @DelimitedField(name="vote")
    Integer vote;

    @DelimitedField(name="seatClassId")
    Integer seatClassId;

    @DelimitedField(name="price")
    Integer price;

    @DelimitedField(name="rowName")
    String rowName;

    @DelimitedField(name="capacity")
    String capacity;

    @DelimitedField(name="occupied")
    String occupied;

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

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
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

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }
}
