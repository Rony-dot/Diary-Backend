package com.rony.notepadbackend.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Profile ("prod")
@Service
public class ObjectStorageService implements FileService{

    @Value("${s3.bucket.name}")
    private String BUCKET_NAME;
    private final AmazonS3 amazonS3;
    private final String PATH_OF_FILE = "images";

    public ObjectStorageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String writeToDirectory(MultipartFile multipartFile) {

        //check if the file is empty
        if (multipartFile.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        if (!Arrays.asList(ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_BMP.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType(),
                ContentType.IMAGE_JPEG.getMimeType()).contains(multipartFile.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<> ();
        metadata.put("Content-Type", multipartFile.getContentType());
        metadata.put("Content-Length", String.valueOf(multipartFile.getSize()));
        //Save Image in S3 and then save Todo in the database
        String path = String.format("%s/%s", BUCKET_NAME, PATH_OF_FILE);
        String fileName = String.format("%s", multipartFile.getOriginalFilename());
        try {
            this.upload(path, fileName, Optional.of(metadata), multipartFile.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        return fileName;
    }

    @Override
    public byte[] readFromPath(String fileName) {
        try {
            if (fileName.contains (System.getProperty ("user.home"))) {
                var is = new FileInputStream (fileName);
                return IOUtils.toByteArray(is);
            } else {
                S3Object object = amazonS3.getObject(BUCKET_NAME, PATH_OF_FILE + "/" +fileName);
                S3ObjectInputStream objectContent = object.getObjectContent();
                return IOUtils.toByteArray(objectContent);
            }
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }

    private void upload(String path,
                        String fileName,
                        Optional<Map<String, String>> optionalMetaData,
                        InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            amazonS3.putObject(path, fileName, inputStream, objectMetadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }
}
