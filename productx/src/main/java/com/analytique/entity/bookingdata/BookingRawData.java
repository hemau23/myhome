package com.analytique.entity.bookingdata;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class BookingRawData {

    @Id
    String Id;

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

    @DelimitedField(name="price")
    Integer price;

    @DelimitedField(name="rowName")
    String rowName;

    @DelimitedField(name="capacity")
    Integer capacity;

    @DelimitedField(name="occupied")
    Integer occupied;

    @DelimitedField(name="className")
    String className;

    @DelimitedField(name="seatCode")
    String seatCode;

    @DelimitedField(name="externalTheaterCode")
    String externalTheaterCode;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
