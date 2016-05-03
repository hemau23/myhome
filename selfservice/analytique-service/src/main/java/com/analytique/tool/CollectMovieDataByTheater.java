package com.analytique.tool;

import com.analytique.exception.AnalytiqueException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CollectMovieDataByTheater {


    String url="http://in.bookmyshow.com/";
    Document getShowTimeByMovie(){
        try {
            return Jsoup.connect(url + "/buytickets/city-pride-kothrud/cinema-pune-CPKT-MT/20151022").get();

        } catch (IOException e) {
            throw  new AnalytiqueException("Not able to fetch GetShowTimesByEvent from url" + e);
        }
    }



    void getAllShowTimeByMovie() throws IOException {

        String test="Aa";
        String test1="BB";
        int i = test.hashCode();
        int i1 = test1.hashCode();
        System.out.println(i+"="+i1);

        final Integer t;


        t=234;

        System.out.println(t);
       /* Document showTimeByMovie = getShowTimeByMovie();

        Iterator<Element> iterator = showTimeByMovie.select(".list").select(".body").select("a").iterator();
        while (iterator.hasNext()){
            Element movie = iterator.next();
            String showTime = movie.text();
            String onclick = movie.attr("onclick");
            String[] fnSelShs = onclick.split("fnSelSh")[1].split(",");
            String theaterCode=fnSelShs[1].replace("'","").trim();
            String p1Code=fnSelShs[2].replace("'","").trim();
            String movieCode=fnSelShs[3].replace("'","").trim();
            *//*String ua = "Chrome/12.0.742.122 Safari/534.30";

            Document document = Jsoup.connect(url + "doTrans.bms")
                    .userAgent(ua)
                    .header("a","WEB")
                    .header("v",theaterCode)
                    .header("t","0")
                    .header("c","GETSEATLAYOUT")
                    .header("p1",p1Code)
                    .header("p2","WEB")
                    .header("p3","")
                    .header("p4","")
                    .header("p5","Y")
                    .header("p6","")
                    .header("p7","")
                    .header("p8","")
                    .header("p9","")
                    .header("p10", "")
                    .post();*//*
            System.out.println();
        }*/

    }
/*
    public static void main(String[] args) {


        try {
            new CollectMovieDataByTheater().getAllShowTimeByMovie();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
