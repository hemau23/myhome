package com.analytique.tool;


import com.analytique.exception.AnalytiqueException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CollectMovieInformation {

    public static final String HEADER_LIST="movieName,externalMovieCode,duration,certificate,isHitSongs,releaseDate,crew,genres,ratings\n";
    String url="http://in.bookmyshow.com/";
    String fileName="c:/filename.mov";



    Document fetchFromUrl(String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw  new AnalytiqueException("Not able to fetch data from url" + e);
        }
    }

    BufferedWriter initiateCSVWritter(){
        try {
            File file = new File(fileName);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            return new BufferedWriter(fw);
        }catch (Exception e){
            throw  new AnalytiqueException("Not able to create file" + e);
        }
    }

    private void writeToFile() {
        BufferedWriter bufferedWriter = initiateCSVWritter();
        try {
            Document doc = fetchFromUrl(url+"pune/movies/");
            ListIterator<Element> li = doc.select(".__movie-list").select("li").listIterator();
            bufferedWriter.write(HEADER_LIST);
            while (li.hasNext()) {
                Element element = li.next();
                String movieName = element.select(".__name").text();
                String movieCode= element.attr("data-event-code").toString();
                Document movie = Jsoup.connect(url + element.select(".__name").attr("href")).get();
                String writerName= movie.select(".__writer-name a").text();
                String composerName = movie.select(".__composer-name a").text();
                String directorName= movie.select(".__director-name a").text();
                String percentage= movie.select(".__percentage").text();
                String ratingStars= movie.select(".rating-stars").attr("data-value");
                Iterator<Element> genreIterator = movie.select(".__genre-tag").iterator();
                String certificate = movie.select(".__censor use").attr("xlink:href").split("#")[1].replace("icon-", "");
                String movieDuration= movie.select(".__time").text();
                String[] hrs = movieDuration.split("hrs");
                if (hrs.length>0) {
                    movieDuration= Integer.parseInt(hrs[0].trim())*60+Integer.parseInt(hrs[1].split("mins")[0].trim())+"";
                }
                String realeseDate= movie.select(".__release-date").text();
                String genres="";
                while (genreIterator.hasNext()) {
                    genres+="|"+genreIterator.next().text().trim();
                }
                genres=genres.substring(1);
                //genres=genres.replace(genres.substring(genres.length()-1), "");
                Iterator<Element> castIterator = movie.select(".__cast-member").iterator();
                String cast="";
                while (castIterator.hasNext()) {
                    Element castName = castIterator.next();
                    cast+=castName.text()+":support|";
                }

                String castAndCrew=cast+writerName+":Writer|"+composerName+":Music Director|"+directorName+":Director";
                StringBuffer line= new StringBuffer();
                line.append(movieName).append(",");
                line.append(movieCode).append(",");
                line.append(movieDuration).append(",");
                line.append(certificate.toUpperCase()).append(",");
                line.append("").append(",");
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
                Date parseDate = formatter.parse(realeseDate);
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                line.append(formatter.format(parseDate).toString()).append(",");
                line.append(castAndCrew).append(",");
                line.append(genres).append(",");
                line.append(ratingStars).append("\n");
                bufferedWriter.write(line.toString());
            }
        }
        catch(Exception e) {
            throw  new AnalytiqueException("failed in conversion for movie"+e);
        }
        finally {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   /* public static void main(String[] args) {
        new CollectMovieInformation().writeToFile();
    }*/
}
