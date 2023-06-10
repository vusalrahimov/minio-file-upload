package io.desofme.miniofileupload;

import io.desofme.miniofileupload.config.properties.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MinioProperties.class)
public class MinioFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioFileUploadApplication.class, args);
    }

}