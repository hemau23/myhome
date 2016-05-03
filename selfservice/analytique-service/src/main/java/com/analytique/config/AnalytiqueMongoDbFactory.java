package com.analytique.config;

import com.mongodb.MongoClient;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

public class AnalytiqueMongoDbFactory extends SimpleMongoDbFactory {

    private final AnalytiqueMongoExceptionTranslator analytiqueMongoExceptionTranslator;

    public AnalytiqueMongoDbFactory(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
        this.analytiqueMongoExceptionTranslator = new AnalytiqueMongoExceptionTranslator();
    }

    @Override
    public PersistenceExceptionTranslator getExceptionTranslator() {
        return analytiqueMongoExceptionTranslator;
    }
}


