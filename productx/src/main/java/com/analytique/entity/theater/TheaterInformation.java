package com.analytique.entity.theater;

import org.springframework.data.annotation.Id;

public class TheaterInformation {

    @Id
    String theaterId;
    String externalTheaterCode;
    String city;
    Boolean isMultiplex;
    Integer numberOfScreen;
    String address;

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
