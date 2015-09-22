package com.analytique.entity.theater;

import com.analytique.file.annotation.DelimitedField;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

/**
 * Created by hemant on 9/21/2015.
 */
public class TheaterRawInformation {

    @Id
    String Id;

    @DelimitedField(name="externalTheaterCode")
    String externalTheaterCode;

    @DelimitedField(name="city")
    String city;

    @DelimitedField(name="isMultiplex")
    Boolean isMultiplex;

    @DelimitedField(name="numberOfScreen")
    Integer numberOfScreen;

    @DelimitedField(name="address")
    String address;

    @DelimitedField(name="seatClassMap")
    Map<String,String> seatClassMap;

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

    public Map<String, String> getSeatClassMap() {
        return seatClassMap;
    }

    public void setSeatClassMap(Map<String, String> seatClassMap) {
        this.seatClassMap = seatClassMap;
    }
}
