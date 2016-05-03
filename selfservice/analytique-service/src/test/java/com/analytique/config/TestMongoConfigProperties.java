package com.analytique.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestMongoConfigProperties {

    @Value("${test.ngi.database.name:test-ngi}")
    private String ngiDatabaseName;

    public String getNGIDatabaseName() {
        return ngiDatabaseName;
    }

    public void setNGIDatabaseName(String ngiDatabaseName) {
        this.ngiDatabaseName = ngiDatabaseName;
    }
}
