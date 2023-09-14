package com.banking.s3Aws.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {

     @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;// = "AKIATO7MDQR6VN6EPP";

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;// = "fQHlMXGjXZpjTeLtrcVnPeOwIFrKwqWS7Ivn4o";

    @Value("${cloud.aws.region.static}")
    private String region;// = "eu-west";

    @Bean
    public AmazonS3 s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region).build();
    }


}
