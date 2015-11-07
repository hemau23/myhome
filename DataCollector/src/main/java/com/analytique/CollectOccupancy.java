package com.analytique;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hemau23 on 10/28/2015.
 */
public class CollectOccupancy {

    private static BufferedWriter bw;
    String baseUrl = "http://in.bookmyshow.com/";
    final static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh:mm aa");
    final static SimpleDateFormat toDateAndTime = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    final static SimpleDateFormat toDate = new SimpleDateFormat("yyyyMMdd");

    private String getSeatLayout(String theaterCode, String showCallCode) throws IOException {
        String strdata = "";
        try {
            Document post = Jsoup.connect(baseUrl + "serv/doTrans.bms?a=WEB&v=" + theaterCode + "&t=0&c=GETSEATLAYOUT&p1=" + showCallCode + "&p2=WEB&p3=&p4=&p5=&p6=&p7=&p8=&p9=&p10=")
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

    public static void main(String[] args) {
        CollectOccupancy collectOccupancy = new CollectOccupancy();
        Date date = new Date();
        //date = DateUtils.addDays(date, -4);
        File folder = new File("F:\\data\\input");

        if (args.length > 0) {
            folder = new File(args[0]);
        }
        File[] listOfFiles = folder.listFiles();
        try {
            for (File inputFile : listOfFiles) {
                String showDateFromFile = inputFile.getName().split("-")[1].split("\\.")[0];
                Date parse = toDate.parse(showDateFromFile);

                String showDate = toDate.format(parse);
                // File inputFile = new File("F:\\data\\MovieInfo-" + showDate + ".txt");
                File file = new File("F:\\data\\output\\mydata-" + showDate + ".txt");
                // if file doesnt exists, then create it
                if (!file.exists() && (new Date().getTime()-parse.getTime()) > 0) {
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);
                    BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile));
                    String line = inputFileReader.readLine();
                    while (line != null) {
                        String[] data = line.split(",");
                        StringBuffer str = new StringBuffer();
                        if (!data[4].equals("null")) {
                            System.out.println("Fetching data for" + data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]);
                            String seatLayout = collectOccupancy.getSeatLayout(data[2], data[4]);
                            if (!seatLayout.equals("")) {
                                bw.write(str.append(data[2]).append(",")
                                        .append(data[0]).append(",")
                                        .append(data[3]).append(",")
                                        .append(data[1]).append(",")
                                        .append(data[4]).append(",")
                                        .append(seatLayout).append("\n").toString());
                            } else {
                                System.out.println("Not found data for" + data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]);
                            }
                        }

                        bw.flush();
                        line = inputFileReader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw!=null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
