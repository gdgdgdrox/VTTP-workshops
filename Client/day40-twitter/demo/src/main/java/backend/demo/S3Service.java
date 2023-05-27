package backend.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Service
public class S3Service {
    
    @Autowired
    private AmazonS3 awsS3;

    public String saveImage(MultipartFile file){
        
        //user data
        // Map<String,String> userData = new HashMap<>();
        // userData.put("")

        //Object Metadata
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        
        //Create a PutObjectRequest
        String key = UUID.randomUUID().toString().substring(0,8);
        System.out.println("KEY " + key);
        String bucketName = "gd-twitter-demo";
        String imagePath = "";
        try {
            PutObjectRequest putObjRequest = new PutObjectRequest(bucketName, "gd-twitter-demo/%s".formatted(key), file.getInputStream(), metadata);
            putObjRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult putObjResult = awsS3.putObject(putObjRequest);
            

            //To Do: instead of passing the image link to client, we should create a handler for client's GET request for image
            StringBuilder sb = new StringBuilder();
            //https://gd-bucket.sgp1.digitaloceanspaces.com/gd-image%2Fd97e8472
            imagePath = sb.append(bucketName).append(".").append("sgp1.digitaloceanspaces.com/").append("gd-twitter-demo/").append(key).toString();
            System.out.println("IMAGE PATH " + imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imagePath;
        
    }
}
