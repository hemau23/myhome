package com.analytique;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    public  Map<String,String> getRatingDetails(String eventCode){

        Map<String, String> ratingsDetails = new HashMap<String,String>();


        try {

            URL url = new URL("http://localhost:8080/movieInformation/search/findByMovieExternalCode?movieExternalCode="+eventCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            StringBuffer stringBuffer= new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                stringBuffer.append(inputLine);
            in.close();

            JSONArray jsonArray =  (JSONArray)new JSONObject(new JSONObject(stringBuffer.toString()).get("_embedded").toString()).get("movieInformation");
            String fshareURL = ((JSONObject) jsonArray.get(0)).get("fshareURL").toString();
            String youtubeTrailerUrl = ((JSONObject) jsonArray.get(0)).get("trailerUrl").toString();
            String movieName = ((JSONObject) jsonArray.get(0)).get("movieName").toString().replaceAll(" ","+");

            Document movie = Jsoup.connect(fshareURL).timeout(60000).get();
            String percentage= movie.select(".__percentage").text();
            if (percentage.equals("")) System.out.println("FAILED: while geeting percentage data");
                ratingsDetails.put("percentage",percentage);


            String votes = movie.select(".__votes").text();
            if (votes.equals("")) System.out.println("FAILED: while geeting votes data");
            ratingsDetails.put("votes", votes.replaceAll("votes", "").replaceAll(",", ""));


            String criticsRating=movie.select(".critic-rating").select(".rating-stars").attr("data-value");
            if (criticsRating.equals("")) System.out.println("FAILED: while geeting criticsRating data");
            ratingsDetails.put("criticsRatings",criticsRating);


            String userRatings=movie.select(".user-rating").select(".rating-stars").attr("data-value");
            if (userRatings.equals("")) System.out.println("FAILED: while geeting  userRatings data");
            ratingsDetails.put("userRatings",userRatings);

            String youTubeHits = Jsoup.connect(youtubeTrailerUrl).get().body().select(".watch-view-count").text().replaceAll(",","");
            if (youTubeHits.equals("")) System.out.println("FAILED: while geeting  youTube data");
            ratingsDetails.put("youTubeHits",youTubeHits);



            url = new URL("http://www.omdbapi.com/?t=" + movieName + "&y=&tomatoes=true");
            in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            stringBuffer= new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                stringBuffer.append(inputLine);
            in.close();

            String tomatoesRatings=new JSONObject(stringBuffer.toString()).get("tomatoUserRating").toString();
            ratingsDetails.put("tomatoesRatings",tomatoesRatings);
            String tomatoUserReviewsCount=new JSONObject(stringBuffer.toString()).get("tomatoUserReviews").toString().replaceAll(",", "");
            ratingsDetails.put("tomatoUserReviewsCount",tomatoUserReviewsCount);
            String imdbRatings =new JSONObject(stringBuffer.toString()).get("imdbRating").toString();
            ratingsDetails.put("imdbRatings",imdbRatings);
            String imdbVotesCount = new JSONObject(stringBuffer.toString()).get("imdbVotes").toString().replaceAll(",","");
            ratingsDetails.put("imdbVotesCount",imdbVotesCount);
        }
        catch(Exception e) {
            System.out.println("FAILED : while getting rating data");;
        }

        return ratingsDetails;
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
                if (!file.exists() ) {
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);
                    bw.write("externalTheaterCode,movieName,showDateTime,movieExternalCode,showUniqueId,percentage,votes,criticsRatings,userRatings,youTubeHits,tomatoesRatings,tomatoUserReviewsCount,imdbRatings,imdbVotesCount,seatMap\n");
                    BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile));
                    String line = inputFileReader.readLine();
                    while (line != null) {
                        String[] data = line.split(",");

                        Map<String, String> ratingDetails = collectOccupancy.getRatingDetails(data[1]);

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
                                        .append(ratingDetails.get("percentage")).append(",")
                                        .append(ratingDetails.get("votes")).append(",")
                                        .append(ratingDetails.get("criticsRatings")).append(",")
                                        .append(ratingDetails.get("userRatings")).append(",")
                                        .append(ratingDetails.get("youTubeHits")).append(",")
                                        .append(ratingDetails.get("tomatoesRatings")).append(",")
                                        .append(ratingDetails.get("tomatoUserReviewsCount")).append(",")
                                        .append(ratingDetails.get("imdbRatings")).append(",")
                                        .append(ratingDetails.get("imdbVotesCount")).append(",")
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
