package com.analytique.transformer.movie;

import com.analytique.entity.monitor.ErrorMessage;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessagingException;

import java.io.File;

public class MovieBookingDataTransformer implements GenericTransformer<File,String> {


    @Override
    public String transform(File source) {

        return null;
    }
}
