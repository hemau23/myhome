package com.analytique;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hemau23 on 10/28/2015.
 */
public class Collector {

    public static List<String> theaterList = new ArrayList<String>();
    static SimpleDateFormat toDateAndTime = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    static SimpleDateFormat toDate = new SimpleDateFormat("yyyyMMdd");
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh:mm aa");
    private static BufferedWriter bufferedWriter;
    private String baseUrl = "http://in.bookmyshow.com/";

    List<MovieInfo> getShowDetails(String theaterPath, String showDate) {

        List<MovieInfo> movieInfoList = new ArrayList<MovieInfo>();
        Document document = fetchDocument("buytickets/" + theaterPath + "/" + showDate);
        Iterator<Element> iterator = document.select(".list").iterator();
        while (iterator.hasNext()) {
            MovieInfo movieInfo = new MovieInfo();
            Element next = iterator.next();
            String href = next.select(".info").select(".__name").attr("href");
            if (href.equals(""))
                continue;
            movieInfo.setTheaterCode(theaterPath.split("/")[1].split("-")[2]);
            movieInfo.setMovieExternalCode(href.split("/")[3].split("-")[2]);
            movieInfo.setMovieName(next.select(".info").select(".__name").text());
            Iterator<Element> showIteratorForAvailale = next.select(".body").select("a").iterator();
            List<ShowCallInfo> showCallInfolist = new ArrayList<ShowCallInfo>();
            while (showIteratorForAvailale.hasNext()) {
                ShowCallInfo showCallInfo = new ShowCallInfo();
                Element showInfoElement = showIteratorForAvailale.next();
                String showTime = showInfoElement.select("a").text();
                String showInfo = showInfoElement.attr("onclick");
                showCallInfo.setShowDate(showDate);
                showCallInfo.setShowTime(showTime);
                String[] split = showInfo.split(",");
                if (!showInfo.equals("")) {
                    showCallInfo.setShowUniqueId(Integer.parseInt(split[2].replace("'", "").trim()));
                }
                showCallInfolist.add(showCallInfo);
            }
            movieInfo.setShowCallDetails(showCallInfolist);
            movieInfoList.add(movieInfo);
        }
        return movieInfoList;
    }

    private Document fetchDocument(String theaterPath) {
        try {
            return Jsoup.connect(baseUrl + theaterPath)
                    .timeout(60000)
                    .get();

        } catch (IOException e) {
            System.out.println("Not able to connect to " + baseUrl + theaterPath + e);
        }
        return null;
    }

    Date convertToDate(String showDate, String showTime) {
        try {
            String dateStr = showDate + " " + showTime.trim();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd hh:mm aa");
            TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
            simpleDateFormat.setTimeZone(istTimeZone);
            return simpleDateFormat.parse(dateStr);

        } catch (Exception e) {
            try {
                throw new Exception("Failed while conversion str to date" + e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> theater = new ArrayList<String>();
        theater.add("esquare-university-road/cinema-pune-ESPN-MT/");
        theater.add("victory-theatre-camp-pune/cinema-pune-ESVP-MT/");
        theater.add("esquare-konark-kondhwa/cinema-pune-ESKP-MT");
        theater.add("esquare-vishal-pimpri/cinema-pune-ESPM-MT");

        Date date = new Date();
        // Date date = DateUtils.addDays(date1, 1);

        try {
            for (int i=0;i<3;i++) {
                TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
                toDate.setTimeZone(istTimeZone);
                toDateAndTime.setTimeZone(istTimeZone);
                formatter.setTimeZone(istTimeZone);
                String showDate = toDate.format(date);
                File file = new File("F:\\data\\input\\MovieInfo-" + showDate + ".txt");

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    System.out.println("Running for " + date);
                    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                    bufferedWriter = new BufferedWriter(fw);

                    file.createNewFile();
                    for (String urlPath : theater) {
                        List<MovieInfo> showDetails = new Collector().getShowDetails(urlPath, showDate);
                        for (MovieInfo movieInfo : showDetails) {
                            StringBuffer line = new StringBuffer();
                            for (ShowCallInfo showCallInfo : movieInfo.getShowCallDetails()) {
                                line.append(movieInfo.getMovieName()).append(",")
                                        .append(movieInfo.getMovieExternalCode()).append(",")
                                        .append(movieInfo.getTheaterCode()).append(",")
                                        .append(showCallInfo.getShowDate()).append(" ").append(showCallInfo.getShowTime()).append(",")
                                        .append(showCallInfo.getShowUniqueId()).append("\n");
                            }
                            bufferedWriter.write(line.toString());
                            bufferedWriter.flush();
                        }
                    }
                }
                date = DateUtils.addDays(date, 1);
            }
        } catch (Exception e) {
            System.out.println("not able to create file");
        }
        finally {


            try {
                if(bufferedWriter!=null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

