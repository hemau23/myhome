package com.analytique.repository.bookingdata;


import com.analytique.entity.bookingdata.BookingRawData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookingRawDataRepository  extends MongoRepository<BookingRawData, String> {


}
