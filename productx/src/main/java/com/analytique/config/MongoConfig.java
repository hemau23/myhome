package com.analytique.config;

import com.analytique.exception.AnalytiqueException;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import java.net.UnknownHostException;


/**
 * Defines beans and configuration for using MongoDB.
 */
@Configuration
@EnableMongoAuditing
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.connection.per.host:1000}")
    private Integer connectionPerHost;

    @Autowired
    private MongoConfigProperties mongoConfigProperties;

    @Override
    @Bean
    public Mongo mongo() throws UnknownHostException {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        MongoClientOptions options = builder.connectionsPerHost(connectionPerHost).build();
        return new MongoClient(new ServerAddress(mongoConfigProperties.getHost(), mongoConfigProperties.getPort()), options);
    }

    @Override
    protected String getDatabaseName() {
        return mongoConfigProperties.getNGIDatabaseName();

    }

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, getDatabaseName());
    }

    @Bean
    public GridFsTemplate gridFsTemplate() {
        try {
            return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
        } catch(Exception e) {
            throw new AnalytiqueException("Failed to construct mongo template", e);
        }
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

}
