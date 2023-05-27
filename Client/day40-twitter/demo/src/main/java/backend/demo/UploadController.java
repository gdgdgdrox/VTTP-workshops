package backend.demo;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private S3Service s3Service;
    
    @PostMapping(path="upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handlePost(
        @RequestPart MultipartFile file,
        @RequestPart String title,
        @RequestPart String comment
    ){
        System.out.println("IN CONTROLLER");
        // System.out.println("TITLE: " + title);
        // System.out.println("TITLE: " + comment);
        String imagePath = s3Service.saveImage(file);
        JsonObject responseBody = Json.createObjectBuilder().add("imagePath", imagePath).build();
        return ResponseEntity.ok(responseBody.toString());
    }
}

// String name = file.getName();
// String originalName = file.getOriginalFilename();
// long size = file.getSize();
// String contentType = file.getContentType();
// System.out.printf("Name: %s\nOriginal Name: %s\nSize: %d\nContent-Type: %s", name,fileName,size,contentType);