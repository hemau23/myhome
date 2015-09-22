package com.analytique.transformer.movie;

import com.analytique.entity.bookingdata.BookingRawData;
import com.analytique.entity.movie.BookingData;
import com.analytique.entity.movie.MovieInformation;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemant on 9/21/2015.
 */
@Component
public class MovieInformationTransformer implements GenericTransformer<List<MovieInformation>,List<MovieInformation>> {
    @Override
    public List<MovieInformation> transform(List<MovieInformation> source) {

        List<MovieInformation> movieInformations= new ArrayList<>();
        for (MovieInformation movieInformation :source){
            
        }
        return null;
    }
}
