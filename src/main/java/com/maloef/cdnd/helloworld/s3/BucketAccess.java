package com.maloef.cdnd.helloworld.s3;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@Slf4j
public class BucketAccess {

    @Value("${s3.image-bucket}")
    private String imageBucket;

    private AmazonS3 s3;

    @PostConstruct
    public void init() {
        s3 = AmazonS3ClientBuilder.standard().build();
    }

    public void upload(String filename) {
        try {
            String content = "Hello Master, this file is " + filename;
            File tempFile = File.createTempFile(filename, "");
            FileWriter writer = new FileWriter(tempFile);
            writer.write(content);
            writer.close();

            s3.putObject(imageBucket, filename, tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
