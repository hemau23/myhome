package com.analytique.tool;

import com.analytique.exception.AnalytiqueException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollectTheaterInformation {

    String url="http://in.bookmyshow.com/";
    Document getShowTimeByMovie(String movieCodeName,String showDate,String city){
        try {
            Document connect = Jsoup.connect(url + "/buytickets/city-pride-kothrud/cinema-pune-CPKT-MT/20151022").get();
            return Jsoup.connect(url)
                    .header("file","/data/js/GetShowTimesByEvent_"+city+"_"+movieCodeName+"_"+showDate+")}.js")
                    .header("cmd", "GETSHOWTIMESBYEVENTWEB")
                    .header("ec",movieCodeName)
                    .header("dc",showDate)
                    .header("rc", city)
                    .get();
        } catch (IOException e) {
            throw  new AnalytiqueException("Not able to fetch GetShowTimesByEvent from url" + e);
        }
    }

    public void fetchShowTimeForSpecificListOfTheater(List<String> theatherCodes){


        List<String> movieCodeList= new ArrayList<>();
        movieCodeList.add("ET00033570");

        for (String movieCode: movieCodeList) {
            Document pune = getShowTimeByMovie(movieCode, "20151021", "PUNE");
            System.out.println();
        }
    }
 /*   public static void main(String[] args) {


        new CollectTheaterInformation().fetchShowTimeForSpecificListOfTheater(new ArrayList<>());
    }*/

}
