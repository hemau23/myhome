package com.analytique.repository.alldata;

import com.analytique.entity.alldata.OneFileAllData;
import com.analytique.entity.movie.BookingData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by hemau23 on 11/7/2015.
 */

@RepositoryRestResource(collectionResourceRel = "oneFileAllData", path = "oneFileAllData")
public interface OneFileAllDataRepository extends MongoRepository<OneFileAllData,String> {

    List<OneFileAllData> findByMovieName(@Param("movieName") String movieName);
    List<OneFileAllData> findByExternalTheaterCode(@Param("externalTheaterCode") String externalTheaterCode);

}

