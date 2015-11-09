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
