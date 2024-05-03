package com.communityconnect.spring.service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {
        @Value("${cloud.aws.s3.bucket}")
        private String bucket;

        @Autowired
        private S3Client s3Client;

        public void uploadFileToS3(String key, MultipartFile file) throws IOException {
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                .bucket(bucket) // Assuming 'bucket' is a class variable holding your bucket name
                                .key(key)
                                .build();

                // Convert the MultipartFile to a RequestBody
                RequestBody requestBody = RequestBody.fromInputStream(file.getInputStream(), file.getSize());

                // Upload the file to S3
                s3Client.putObject(putObjectRequest, requestBody);
        }

        /* Create a pre-signed URL to download an object in a subsequent GET request. */
        public String createPresignedGetUrl(String keyName) {
                try (S3Presigner presigner = S3Presigner.create()) {

                        GetObjectRequest objectRequest = GetObjectRequest.builder()
                                        .bucket(bucket)
                                        .key(keyName)
                                        .build();

                        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                                        .signatureDuration(Duration.ofMinutes(10)) // The URL will expire in 10 minutes.
                                        .getObjectRequest(objectRequest)
                                        .build();

                        PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
                        log.info("Presigned URL: [{}]", presignedRequest.url().toString());
                        log.info("HTTP method: [{}]", presignedRequest.httpRequest().method());

                        return presignedRequest.url().toExternalForm();
                }
        }
}