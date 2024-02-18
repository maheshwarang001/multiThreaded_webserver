package com.example.imageservice.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.imageservice.entity.ImageMetaDataEntity;
import com.example.imageservice.model.MetaDataImage;
import com.example.imageservice.utils.FileConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private ImageMetaDataEntity imageMetaData;

    @Autowired
    private FileConverter fileConverter;



    public MetaDataImage servicegetmeta(UUID uuid) {
        return imageMetaData.getMetaData(uuid);
    }

    private boolean uploadFile(File multipartFile, String userId, String url,String name) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/png");

        try {
            s3Client.putObject(new PutObjectRequest(bucketName, name, multipartFile).withMetadata(metadata));

            // Successful upload

            MetaDataImage metaDataImage = new MetaDataImage();
            metaDataImage.setUuid(UUID.fromString(userId));
            metaDataImage.setImages(url);

            updateDBAsync(metaDataImage);

            multipartFile.delete();

        } catch (SdkClientException e) {
            log.error("Error uploading file: {}", e.getMessage());
            return false;
        }

        return true;
    }

    public void checker(MultipartFile multipartFile, String userId) throws IOException {
        File file = converter(multipartFile);
        String suffix = userId.hashCode() + "_" + multipartFile.getOriginalFilename();
        String imageUrl = bucketName + ".s3." + region + ".amazonaws.com/" + suffix;

        MetaDataImage metaDataImage = new MetaDataImage();
        metaDataImage.setUuid(UUID.fromString(userId));
        metaDataImage.setImages(imageUrl);

        if (imageMetaData.existsByUUID(metaDataImage.getUuid()) && !imageMetaData.exisitsByUrl(metaDataImage)) {
            deleteObjectAsync(imageUrl);
        }

        uploadFile(file, userId, imageUrl,suffix);
    }

    private File converter(MultipartFile multipartFile) throws IOException {
        return fileConverter.convertMultiPartFiletoFile(multipartFile);
    }

    private void updateDBAsync(MetaDataImage metaDataImage) {
       updateDB(metaDataImage);

    }

    private void deleteObjectAsync(String imageUrl) {
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, imageUrl);
            s3Client.deleteObject(deleteObjectRequest);
    }

    private boolean updateDB(MetaDataImage metaDataImage) {
        try {
            imageMetaData.saveMetaData(metaDataImage);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public boolean checkExistByUuid(UUID id){
        return imageMetaData.existsByUUID(id);
    }



    public List<MetaDataImage> getAllMetaData(List<UUID> uuids){
        return imageMetaData.getAllData(uuids);
    }
}
