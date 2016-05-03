package com.analytique.repository;


import com.analytique.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/2/2015.
 */
@RepositoryRestResource
public interface CustomerRepository  extends MongoRepository<Customer, String> {
}
