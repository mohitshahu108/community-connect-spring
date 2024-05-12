package com.communityconnect.spring.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Bean
    S3Client s3Client() {
        try {
            S3Client s3Client = S3Client.builder()
                    .region(Region.AP_SOUTH_1)
                    .endpointOverride(new URI("https://s3.ap-south-1.amazonaws.com")) // This line can throw //
                                                                                      // URISyntaxException
                    .build();
            return s3Client;
        } catch (SdkClientException | URISyntaxException e) { // Add URISyntaxException to the catch clause
            // Handle exception, log error, etc.
            throw new RuntimeException("Error creating S3 client", e);
        }
    }
}
