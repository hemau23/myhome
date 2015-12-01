package com.analytique.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoConfigProperties {

    @Value("${spring.data.mongodb.host:127.0.0.1}")
    private String host;

    @Value("${spring.data.mongodb.port:27017}")
    private int port;

    @Value("${analytique.database.name:analytique}")
    private String dataBaseName;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getNGIDatabaseName() {
        return dataBaseName;
    }

    public void setNGIDatabaseName(String ngiDatabaseName) {
        this.dataBaseName = ngiDatabaseName;
    }
}
