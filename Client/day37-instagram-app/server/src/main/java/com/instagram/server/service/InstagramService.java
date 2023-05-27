package com.instagram.server.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.instagram.server.model.InstagramPost;
import com.instagram.server.repository.ImageRepository;
import com.instagram.server.repository.InstagramRepository;
import com.instagram.server.repository.VoteRepository;

@Service
public class InstagramService {
    
    @Autowired
    private InstagramRepository instagramRepo;

    @Autowired
    private ImageRepository imageRepo;

    @Autowired
    private VoteRepository voteRepo;

    public String saveInstagramPost(MultipartFile image, String requestBody){
        Document doc = Document.parse(requestBody);

        //remove unnecessary field
        doc.remove("image");

        //generate a unique key
        String postId = UUID.randomUUID().toString().substring(0,8);
        doc.put("post_id", postId);

        //add date
        doc.put("date", new Date());

        //save post to MongoDB
        instagramRepo.saveInstagramPost(doc);

        //save image to S3
        boolean uploadSuccess = imageRepo.uploadImage(image, requestBody, postId);
        System.out.println("upload success %b".formatted(uploadSuccess));

        return postId;
    }

    public Optional<Document> geInstagramPost(String postId){
        return instagramRepo.getInstagramPost(postId);
    }

    public byte[] getImage(String postId){
        return imageRepo.getImage(postId);
    }

    public long updateVote(String postId, String updateVote){
        return voteRepo.updateVote(postId, updateVote);
    }

    public void getUpVoteCount(String postId){
        voteRepo.getUpVoteCount(postId);
    }
}
