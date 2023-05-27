package com.instagram.server.repository;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class InstagramRepository {
    private static final String COLLECTION_POST = "post";
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveInstagramPost(Document instagramPost){
        System.out.println("Repo: Saving InstagramPost");
        mongoTemplate.save(instagramPost, COLLECTION_POST);
        System.out.println("Repo: Done");
    }

    public Optional<Document> getInstagramPost(String postId){
        System.out.println("Repo: Getting instagram post with ID: %s".formatted(postId));
        Criteria criteria = Criteria.where("post_id").is(postId);
        Query query = Query.query(criteria);
        return Optional.ofNullable(mongoTemplate.findOne(query, Document.class, COLLECTION_POST));
    }
}
