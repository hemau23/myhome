package com.analytique.entity.theater;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.List;

public class TheaterInformation {

    @Id
    String theaterId;
    String externalTheaterCode;
    String theaterName;
    Boolean isMultiplex;
    Integer numberOfScreen;
    String address;
    String city;
    Integer pinCode;
    String state;
    String country;
    List<String> seatClassIds;


    public List<String> getSeatClassIds() {
        return seatClassIds;
    }

    public void setSeatClassIds(List<String> seatClassIds) {
        this.seatClassIds = seatClassIds;
    }

    public Boolean getIsMultiplex() {
        return isMultiplex;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

      public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }

    public String getExternalTheaterCode() {
        return externalTheaterCode;
    }

    public void setExternalTheaterCode(String externalTheaterCode) {
        this.externalTheaterCode = externalTheaterCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean isMultiplex() {
        return isMultiplex;
    }

    public void setIsMultiplex(Boolean isMultiplex) {
        this.isMultiplex = isMultiplex;
    }

    public Integer getNumberOfScreen() {
        return numberOfScreen;
    }

    public void setNumberOfScreen(Integer numberOfScreen) {
        this.numberOfScreen = numberOfScreen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
