package com.ojdgaf.cloud.user.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("s3")
public class AmazonConfig {

    @Bean
    public AmazonS3 s3(
        @Value("${app.s3.access-key}") final String accessKey,
        @Value("${app.s3.secret-key}") final String secretKey,
        @Value("${app.s3.region}") final String region
    ) {
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.fromName(region))
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
            .build();
    }
}
