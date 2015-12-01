package com.analytique.repository.theater;

import com.analytique.entity.theater.SeatClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by hemant on 9/19/2015.
 */
@RepositoryRestResource(collectionResourceRel = "seatClass", path = "seatClass")
public interface SeatClassRepository extends MongoRepository<SeatClass,String> {
     SeatClass findBySeatCode(@Param("seatCode") String seatCode);
     List<SeatClass> findBySeatCodeAndClassName(@Param("seatCode") String seatCode,@Param("className") String className);
}


