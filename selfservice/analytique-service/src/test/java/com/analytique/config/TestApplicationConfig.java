package com.analytique.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
@Import(ApplicationConfig.class)
public class TestApplicationConfig {
    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo, TestMongoConfigProperties testMongoConfigProperties) {
        return new MongoTemplate(mongo, testMongoConfigProperties.getNGIDatabaseName());
    }
}
