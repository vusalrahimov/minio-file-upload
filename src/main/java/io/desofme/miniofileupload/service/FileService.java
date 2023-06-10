package io.desofme.miniofileupload.service;

import io.desofme.miniofileupload.config.properties.MinioProperties;
import io.desofme.miniofileupload.dto.response.FileUploadResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @SneakyThrows
    public FileUploadResponse upload(MultipartFile multipartFile) {
        InputStream is = new ByteArrayInputStream(multipartFile.getBytes());
        var uuid = UUID.randomUUID();
        String fileName = uuid.toString().concat(".").concat(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        String keyName = minioProperties.getFolder().concat("/").concat(fileName);
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .contentType(multipartFile.getContentType())
                .bucket(minioProperties.getBucket())
                .stream(is, multipartFile.getSize(), -1)
                .object(keyName)
                .build();

        String downloadUrl = minioProperties.getUrl() + "/" + minioProperties.getBucket() + "/" + keyName;

        minioClient.putObject(putObjectArgs);

        return FileUploadResponse.of(downloadUrl);
    }

}