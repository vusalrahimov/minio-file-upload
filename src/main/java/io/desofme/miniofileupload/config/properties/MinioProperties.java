package io.desofme.miniofileupload.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String accessKey;
    private String secretKey;
    private String url;
    private String bucket;
    private String folder;

}
