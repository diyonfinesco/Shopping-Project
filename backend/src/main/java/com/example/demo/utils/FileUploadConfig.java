package com.example.demo.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "upload")
public class FileUploadConfig {
    private String directory;

    public String getDirectory(){
        return directory;
    }

    public void setDirectory(String directory){
        this.directory = directory;
    }
}
