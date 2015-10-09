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

    @DelimitedField(name="seatMap")
    String seatMap;

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
