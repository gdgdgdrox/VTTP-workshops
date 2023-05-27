package com.instagram.server.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Repository
public class ImageRepository {
    
    @Autowired
    private AmazonS3 s3;

    public boolean uploadImage(MultipartFile image, String requestBody, String postId){

        ObjectMetadata objMetaData = new ObjectMetadata();
        objMetaData.setContentLength(image.getSize());
        objMetaData.setContentType(image.getContentType());

        //what happens when we get bucket name wrong? ans: NoSuchBucket exception
        try {
            PutObjectRequest putObjReq = new PutObjectRequest("gd-workshop37-instagram", postId, image.getInputStream(), objMetaData);
            putObjReq.withCannedAcl(CannedAccessControlList.PublicRead);
            System.out.println("Putting Object into S3");
            PutObjectResult rs = s3.putObject(putObjReq);
            System.out.println("Put Object Result: %s".formatted(rs.toString()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public byte[] getImage(String postId){
        GetObjectRequest getObjRequest = new GetObjectRequest("gd-workshop37-instagram", postId);
        System.out.println("GETTING IMAGE WITH KEY %s FROM DIGITAL OCEAN".formatted(postId));
        S3Object s3Obj = s3.getObject(getObjRequest);
        S3ObjectInputStream is = s3Obj.getObjectContent();
        try {
            byte[] buffer = is.readAllBytes();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
