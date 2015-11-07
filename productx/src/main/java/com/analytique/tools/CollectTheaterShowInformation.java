package com.analytique.tools;

import com.analytique.exception.AnalytiqueException;
import com.analytique.util.DateTimeUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by hemau23 on 10/24/2015.
 */
public class CollectTheaterShowInformation implements Runnable {

    private static BufferedWriter bw;
    String url = "http://in.bookmyshow.com/";
    final static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh:mm aa");
    final static SimpleDateFormat toDateAndTime = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    final static SimpleDateFormat toDate = new SimpleDateFormat("yyyyMMdd");
    String urlToCall = "";
    String showDateToexecute = "";

    public String getShowDateToexecute() {
        return showDateToexecute;
    }

    public void setShowDateToexecute(String showDateToexecute) {
        this.showDateToexecute = showDateToexecute;
    }

    public String getUrlToCall() {
        return urlToCall;
    }

    public void setUrlToCall(String urlToCall) {
        this.urlToCall = urlToCall;
    }

    Document getDocument(String path) {

        try {
            return Jsoup.connect(url + path)
                    .timeout(60000)
                    .get();
        } catch (IOException e) {
            throw new AnalytiqueException("Not able to connect to " + url + e);
        }

    }

    void getTheaterShowInfo(String theaterUrl, String showDate, BufferedWriter bw) {
        Document document = getDocument("buytickets/" + theaterUrl + "/" + showDate);


        while (true) {
            Iterator<Element> iterator = document.select(".list").iterator();
            System.out.println("Still running");
            try {
                bw.flush();
                Thread.sleep(15000);
                while (iterator.hasNext()) {
                    String showTime = null;
                    String theaterCode = null;
                    String movieExternalCode;
                    String strdata = "";
                    String collectionDateTime = toDateAndTime.format(new Date());
                    Element next = iterator.next();
                    theaterCode = theaterUrl.split("/")[1].split("-")[2];
                    String href = next.select(".info").select(".__name").attr("href");
                    if (href.equals(""))
                        continue;
                    movieExternalCode = href.split("/")[3].split("-")[2];
                    String movieName = next.select(".info").select(".__name").text();
                    Iterator<Element> showIterator = next.select(".body").select("._sold").iterator();
                    Iterator<Element> showIteratorForAvailale = next.select(".body").select("a").iterator();
                    Map<String, Long> showdateTime;
                    while (showIterator.hasNext()) {
                        strdata = checkHpuseFullOrSoldOut(showDate, bw, theaterCode, movieExternalCode, strdata, collectionDateTime, movieName, showIterator);

                    }

                    while (showIteratorForAvailale.hasNext()) {
                        Element showInfoElement = showIteratorForAvailale.next();
                        showTime = showInfoElement.select("a").text();
                        String showInfo = showInfoElement.attr("onclick");
                        showdateTime = convertToDateTime(showDate, showTime);
                        if (showdateTime != null) {
                            for (String key : showdateTime.keySet()) {
                                if (!showInfo.equals("")) {
                                    String[] split = showInfo.split(",");
                                    theaterCode = split[1].replace("'", "").trim();
                                    String showCallCode = split[2].replace("'", "").trim();
                                    movieExternalCode = split[3].replace("'", "").trim();
                                    strdata = getSeatLayout(theaterCode, showCallCode);
                                    if (!strdata.equals("")) {
                                        System.out.println(theaterCode + "," + collectionDateTime + "," + movieName + "," + movieExternalCode + "," + key + "," + strdata);
                                        bw.write(theaterCode + "," + collectionDateTime + "," + movieName + "," + movieExternalCode + "," + key + "," + strdata + "\n");
                                        bw.flush();
                                    }
                                }

                            }
                        }
                    }

                    bw.flush();

                }
            } catch (Exception e) {
                throw new AnalytiqueException("Failed while main loop" + url + e);
            }
        }
    }

    private String checkHpuseFullOrSoldOut(String showDate, BufferedWriter bw, String theaterCode, String movieExternalCode, String strdata, String collectionDateTime, String movieName, Iterator<Element> showIterator) throws ParseException, IOException {
        String showTime;
        Map<String, Long> showdateTime;
        showTime = showIterator.next().select("a").text();
        showdateTime = convertToDateTime(showDate, showTime);
        if (showdateTime != null) {
            for (String key : showdateTime.keySet()) {
                if (showdateTime.get(key) > 1) {
                    if (showdateTime.get(key) < 5000)
                        strdata = "soldOut";
                } else if (showdateTime.get(key) < -3000000   ) {
                    strdata = "housefull";
                }
                if (!strdata.equals("")) {
                    System.out.println(theaterCode + "," + collectionDateTime + "," + movieName + "," + movieExternalCode + "," + key + "," + strdata);
                    bw.write(theaterCode + "," + collectionDateTime + "," + movieName + "," + movieExternalCode + "," + key + "," + strdata + "\n");
                    bw.flush();
                }
            }
        }
        return strdata;
    }

    private String getSeatLayout(String theaterCode, String showCallCode) throws IOException {
        String strdata = "";
        try {
            Document post = Jsoup.connect(url + "serv/doTrans.bms?a=WEB&v=" + theaterCode + "&t=0&c=GETSEATLAYOUT&p1=" + showCallCode + "&p2=WEB&p3=&p4=&p5=&p6=&p7=&p8=&p9=&p10=")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Referer", "http://in.bookmyshow.com/buytickets/shaandaar-pune/movie-pune-ET00027655-MT/20151025")
                    .header("Origin", "http://in.bookmyshow.com")
                    .header("Accept", "application/xml, text/xml, */*; q=0.01")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; Touch; LCJB; rv:11.0) like Gecko")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Cache-Control", "no-store")
                    .header("DNT", "1")
                    .header("Pragma", "no-cache")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .timeout(60000)
                    .post();
            strdata = post.select("strdata").text();

        } catch (Exception e) {
            System.out.println("FAILED while fetching data" + e);
        }
        return strdata;
    }

    private Map<String, Long> convertToDateTime(String showDate, String showTime) throws ParseException {
        try {
            Map<String, Long> dateTimeDiff = new HashMap<>();
            Date parse = convertToDate(showDate + " " + showTime.trim());
            Long diff = new Date().getTime() - parse.getTime();
            dateTimeDiff.put(toDateTimeString(parse), diff);
            System.out.println(toDateTimeString(new Date()) + "=" + toDateTimeString(parse));
            if (diff > 1 || diff > -3000000 ) {
                return dateTimeDiff;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    String toDateTimeString(Date date) {
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
            simpleDateFormat.setTimeZone(istTimeZone);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            throw new AnalytiqueException("Failed while conversion date to string" + e);
        }
    }


    Date convertToDate(String str) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd hh:mm aa");
            TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
            simpleDateFormat.setTimeZone(istTimeZone);
            return simpleDateFormat.parse(str);

        } catch (Exception e) {
            throw new AnalytiqueException("Failed while conversion str to date" + e);
        }
    }

  /*  public static void main(String[] args) {


        List<String> theater = new ArrayList<>();
          theater.add("esquare-university-road/cinema-pune-ESPN-MT/");
        theater.add("victory-theatre-camp-pune/cinema-pune-ESVP-MT/");
        theater.add("esquare-konark-kondhwa/cinema-pune-ESKP-MT");
        theater.add("esquare-vishal-pimpri/cinema-pune-ESPM-MT");

        Date date = new Date();
        // Date date = DateUtils.addDays(date1, 1);
        TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
        toDate.setTimeZone(istTimeZone);
        toDateAndTime.setTimeZone(istTimeZone);

        formatter.setTimeZone(istTimeZone);
        String showDate = toDate.format(date);
        try {
            File file = new File("F:\\data\\mydata-" + showDate + ".txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            for (String urlPath : theater) {
                CollectTheaterShowInformation collectTheaterShowInformation = new CollectTheaterShowInformation();
                collectTheaterShowInformation.setUrlToCall(urlPath);
                collectTheaterShowInformation.setShowDateToexecute(showDate);
                Thread tobj = new Thread(collectTheaterShowInformation);
                tobj.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/

    @Override
    public void run() {
        System.out.println("My thread is in running state.");
        this.getTheaterShowInfo(this.getUrlToCall(), this.getShowDateToexecute(), this.bw);
    }
}
