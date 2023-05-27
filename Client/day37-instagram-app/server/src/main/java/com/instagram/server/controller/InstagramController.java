package com.instagram.server.controller;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.instagram.server.Utils;
import com.instagram.server.service.InstagramService;

@RestController
@RequestMapping(path="api")
@CrossOrigin(origins="*") 
public class InstagramController {
    @Autowired
    private InstagramService instagramSvc;
    
    @PostMapping(path="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> savePost(@RequestPart MultipartFile image, @RequestPart("instagramPost") String requestBody){
        System.out.println("IN UPLOAD CONTROLLER");
        // System.out.println("File name: %s".formatted(image.getOriginalFilename()));
        // System.out.println("Content type: %s".formatted(image.getContentType()));
        // System.out.println("Size: %d".formatted(image.getSize()));
        // System.out.println(requestBody);

        String postId = instagramSvc.saveInstagramPost(image, requestBody);
        String responseBody = Utils.createJsonObject("post_id", postId).toString();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping(path="/post/image")
    public ResponseEntity<byte[]> getPostImage(@RequestParam String postId){
        System.out.println("IN GET CONTROLLER FOR IMAGE");
        System.out.println("Post ID: %s".formatted(postId));
        // instagramSvc.geInstagramPost(postId);
        return ResponseEntity.ok(instagramSvc.getImage(postId));
    }

    @GetMapping(path="/post/info/{postId}")
    public ResponseEntity<String> getPostInfo(@PathVariable String postId){
        System.out.println("IN GET CONTROLLER FOR IMAGE");
        System.out.println("Post ID: %s".formatted(postId));
        Optional<Document> optDoc = instagramSvc.geInstagramPost(postId);
        if (optDoc.isEmpty()){
            String responseBody = Utils.createJsonObject("error", "cannot locate Post with ID: %s".formatted(postId)).toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }
        Document doc = optDoc.get();
        doc.remove("_id");
        return ResponseEntity.ok(doc.toJson());
    }

    @GetMapping(path="/post/{postId}/{updateVote}")
    public ResponseEntity<String> updateVote(@PathVariable String postId, @PathVariable String updateVote){
        System.out.println("IN VOTE CONTROLLER");
        System.out.println("UPDATE VOTE %s for Post ID %s".formatted(updateVote, postId));
        Long updatedCount = (Long)this.instagramSvc.updateVote(postId, updateVote);
        String responseBody = Utils.createJsonObject(updateVote, updatedCount.toString()).toString();
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping(path="/post/{postId}/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getVote(@PathVariable String postId){
        System.out.println("IN GET COUNT CONTROLLER");
        System.out.println("POST ID: %s".formatted(postId));
        instagramSvc.getUpVoteCount(postId);
        return null;
    }



}
