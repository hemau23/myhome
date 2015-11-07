package com.analytique;

import java.util.List;

/**
 * Created by hemau23 on 10/28/2015.
 */
public class MovieInfo {

    String movieName;
    String movieExternalCode;
    String theaterCode;
    List<ShowCallInfo> showCallDetails;


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieExternalCode() {
        return movieExternalCode;
    }

    public void setMovieExternalCode(String movieExternalCode) {
        this.movieExternalCode = movieExternalCode;
    }

    public String getTheaterCode() {
        return theaterCode;
    }

    public void setTheaterCode(String theaterCode) {
        this.theaterCode = theaterCode;
    }

    public List<ShowCallInfo> getShowCallDetails() {
        return showCallDetails;
    }

    public void setShowCallDetails(List<ShowCallInfo> showCallDetails) {
        this.showCallDetails = showCallDetails;
    }
}
