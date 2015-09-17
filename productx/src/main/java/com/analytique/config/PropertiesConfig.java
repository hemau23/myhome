package com.analytique.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

@Configuration
public class PropertiesConfig {

    public static final String COMPLETED_MGS_CHANNEL = "completedChannel";

    @Value("${str.sftp.remote.directory:c:/incomingDirectory}")
    String incomingDirectoryPath;

    @Value("${str.sftp.remote.directory:c:/archiveDirectory}")
    String archiveDirectoryPath;

    public void setIncomingDirectoryPath(String incomingDirectoryPath) {
        this.incomingDirectoryPath = incomingDirectoryPath;
    }

    public String getIncomingDirectoryPath() {
        return incomingDirectoryPath;
    }

    public File getIncomingDirectory() {
        return new File(incomingDirectoryPath);
    }

    public File getArchiveDirectory() {
        return new File(archiveDirectoryPath);
    }

    public String getArchiveDirectoryPath() {
        return archiveDirectoryPath;
    }
}
