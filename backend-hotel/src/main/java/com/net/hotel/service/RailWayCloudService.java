package com.net.hotel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class RailWayCloudService {
    private final String bucketName="hotel-images";
    @Value("@{aws.s3.access.key}")
    private String awsS3AccessKey;
    @Value("@{aws.s3.access.key}")
    private String awsS3SecretKey;

    public String saveImagesToS3(MultipartFile multipartFile)
    {
        String s3LocationImages=null;

    }

}
