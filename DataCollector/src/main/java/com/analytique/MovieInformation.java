package com.analytique;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hemau23 on 11/29/2015.
 */
public class MovieInformation {


    HttpResponse<JsonNode>  getMovieInformation(String movieCode) {

        String url="https://devru-book-my-show-v1.p.mashape.com/eventInfoList.php?eventcode="+movieCode+"&token=34272aa31x1a6776666a";
        HttpResponse<JsonNode> response=null;
        try {


           response = Unirest.get(url)
                    .header("X-Mashape-Key", "3cjOziFcW3mshdr4pU69iZC13JrCp1aQG0sjsnnMd2Vz4nNuOr")
                    .header("Accept", "application/json")
                    .asJson();

        } catch (Exception e) {
            System.out.println("FAILED while fetching data" + e);
        }

        return response;
    }


    List<String> getEventCodeList(){
        List<String> eventCodeList = new ArrayList<String>();
        try {


            URL url = new URL("http://localhost:8080/movieInformation/search/findByCertificateNull");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            StringBuffer stringBuffer= new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                stringBuffer.append(inputLine);
            in.close();

            JSONArray jsonArray =  (JSONArray)new JSONObject(new JSONObject(stringBuffer.toString()).get("_embedded").toString()).get("movieInformation");

            for(int i=0;i<jsonArray.length();i++){
                String movieExternalCode = ((JSONObject) jsonArray.get(i)).get("movieExternalCode").toString();
                if (!movieExternalCode.equals(""))
                eventCodeList.add(movieExternalCode);
            }

        }
        catch (Exception e){
            System.out.println("FAILED not able to get eventcode list"+ e);
        }

        return eventCodeList;
    }
    public static void main(String[] args) {
        try{

        MovieInformation movieInformation = new MovieInformation();
        List<String> eventCodeList = movieInformation.getEventCodeList();

        File file = new File("F:\\data\\input\\MovieDetails-" + new Date().getTime() + ".csv");
        BufferedWriter bufferedWriter=null;
        // if file doesnt exists, then create it
        if (!file.exists()) {

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fw);

            file.createNewFile();

            List<String> keyList = new ArrayList<String>();
            // keyList.add("Event_MessageTitle");
            //keyList.add("EventType");
            keyList.add("Event_AvgRatings");
            keyList.add("EventMusic");
            keyList.add("EventCensor");
            keyList.add("FShareURL");
            keyList.add("EventCode");
            keyList.add("Ratings");
            keyList.add("Event_Message");
            //keyList.add("EventifierID");
            keyList.add("Language");
            //keyList.add("CanUserRate");
            //keyList.add("IsComingSoon");
            //keyList.add("ImageCode");
            //keyList.add("ProducerCode");
            keyList.add("BannerURL");
            keyList.add("EventTitle");
            keyList.add("EventReleaseDate");
            //keyList.add("EventVoice");
            keyList.add("Director");
            //keyList.add("arrDates");
            //keyList.add("EventIsGlobal");
            keyList.add("Actors");
            keyList.add("Event_UserRatingsCount");
            keyList.add("Event_UserReviewCount");
            keyList.add("EventWriter");
            keyList.add("Event_CriticsRatingsCount");
            keyList.add("Length");
            //keyList.add("EventSynopsis");
            keyList.add("ReleaseDateCode");
            keyList.add("Genre");
            keyList.add("Censor");
            keyList.add("TrailerURL");
            //keyList.add("Seq");
            //keyList.add("GenreArray");
            //keyList.add("strMessage");

            StringBuffer str = new StringBuffer();
            for (String key : keyList) {
                str.append(key).append("|");
            }
            String header = str.toString().substring(0, str.length()-1);
            bufferedWriter.write(header);
            bufferedWriter.write("\n");


            for (String eventCode : eventCodeList) {
                HttpResponse<JsonNode> movieInformation1 = movieInformation.getMovieInformation(eventCode);
                JsonNode body = movieInformation1.getBody();
                JSONArray array = body.getArray();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject data = (JSONObject) ((JSONObject) array.get(i)).get("eventsDetails");
                    str = new StringBuffer();
                    String val=null;
                    for (String key : keyList) {
                        String s = data.get(key).toString();
                        if (s.equals("")) s="null";
                        str.append(s).append("|");
                        System.out.println(data.get(key).toString());
                    }

                    val = str.toString().substring(0, str.length()-1);
                    val+="\n";
                    bufferedWriter.write(val);
                    bufferedWriter.flush();
                }


            }
        }
        }catch(Exception e){
            System.out.println("Failed with some error" + e);
        }
    }
}
