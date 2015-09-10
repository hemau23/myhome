package com.analytique.repository.monitor;


import com.analytique.entity.monitor.ErrorMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/9/2015.
 */
@RepositoryRestResource
public interface ErrorMesssageRepository extends MongoRepository<ErrorMessage, String> {
}
