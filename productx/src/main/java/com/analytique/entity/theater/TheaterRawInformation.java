package com.analytique.entity.theater;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

/**
 * Created by hemant on 9/21/2015.
 */
public class TheaterRawInformation {

    @Id
    String Id;

    @DelimitedField(name="externalTheaterCode")
    String externalTheaterCode;

    @DelimitedField(name = "theaterName")
    String theaterName;

    @DelimitedField(name="isMultiplex")
      Boolean isMultiplex;

    @DelimitedField(name="numberOfScreen")
    Integer numberOfScreen;

    @DelimitedField(name = "address")
    String address;

    @DelimitedField(name = "city")
    String city;

    @DelimitedField(name = "pinCode")
    Integer pinCode;

    @DelimitedField(name = "state")
    String state;

    @DelimitedField(name = "country")
    String country;

    @DelimitedField(name = "seatClassNames")
    String seatClassNames;


    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIsMultiplex() {
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

    public String getSeatClassNames() {
        return seatClassNames;
    }

    public void setSeatClassNames(String seatClassNames) {
        this.seatClassNames = seatClassNames;
    }
}
