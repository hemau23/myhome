package com.analytique.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class PropertiesConfig {

    public static final String COMPLETED_MGS_CHANNEL = "completedChannel";

    @Value("${str.sftp.remote.directory:c:/incomingDirectory}")
    private String incomingDirectory;

    public String getIncomingDirectory() {
        return incomingDirectory;
    }

    public void setIncomingDirectory(String incomingDirectory) {
        this.incomingDirectory = incomingDirectory;
    }
}
