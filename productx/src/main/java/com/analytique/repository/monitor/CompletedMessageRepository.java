package com.analytique.repository.monitor;

import com.analytique.entity.monitor.CompletedMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompletedMessageRepository extends MongoRepository<CompletedMessage, String> {
}
