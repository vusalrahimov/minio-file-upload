package io.desofme.miniofileupload.config;

import io.desofme.miniofileupload.config.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {

    private final MinioProperties minioProperties;

    @Bean
    MinioClient minioClient() {
        return new MinioClient.Builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getUrl())
                .build();
    }


}
