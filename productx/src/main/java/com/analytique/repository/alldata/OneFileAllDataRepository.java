package com.analytique.repository.alldata;

import com.analytique.entity.alldata.OneFileAllData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemau23 on 11/7/2015.
 */

@RepositoryRestResource
public interface OneFileAllDataRepository extends MongoRepository<OneFileAllData,String> {
}
