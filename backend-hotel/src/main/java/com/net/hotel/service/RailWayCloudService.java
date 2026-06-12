package com.net.hotel.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.net.hotel.exception.OurException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.synth.Region;
import java.io.InputStream;

public class RailWayCloudService {
    private final String bucketName="hotel-images";
    @Value("@{aws.s3.access.key}")
    private String awsS3AccessKey;
    @Value("@{aws.s3.access.key}")
    private String awsS3SecretKey;

    public String saveImagesToS3(MultipartFile multipartFile)
    {
        String s3LocationImages=null;

        try{
            String s3FileName=multipartFile.getOriginalFilename();
            BasicAWSCredentials basicAWSCredentials=new BasicAWSCredentials(awsS3AccessKey, awsS3SecretKey);
            AmazonS3 amazonS3= AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                    .withRegion(Regions.US_EAST_2)
                    .build();
            InputStream inputStream=multipartFile.getInputStream();
            ObjectMetadata objectMetadata=new ObjectMetadata();
            objectMetadata.setContentType("image/jpeg");

            PutObjectRequest putObjectRequest=new PutObjectRequest(bucketName,s3FileName,inputStream,objectMetadata);
            amazonS3.putObject(putObjectRequest);
            s3LocationImages="https://"+bucketName+".s3.amazonaws.com/"+s3FileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new OurException("Loi Upload anh");
        }
        return s3LocationImages;

    }

}
