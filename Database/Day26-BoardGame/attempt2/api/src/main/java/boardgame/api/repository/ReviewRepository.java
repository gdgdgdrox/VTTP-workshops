package boardgame.api.repository;


import java.sql.Timestamp;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;

import boardgame.api.model.Comment;

@Repository
public class ReviewRepository {
    private static final String COLLECTION_REVIEWS = "reviews";
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objMapper;

    public String addReview(Document payload){
        System.out.println("INSERTING REVIEW " + payload);
        Document insertedDoc = mongoTemplate.insert(payload, COLLECTION_REVIEWS);
        return insertedDoc.getObjectId("_id").toString();
    }

    public boolean reviewExists(String reviewId){
        Query query = Query.query(Criteria.where("_id").is(reviewId));
        boolean exists = mongoTemplate.exists(query, COLLECTION_REVIEWS);
        return exists;
    }

    public UpdateResult updateReview(Document updatedReview, String reviewId){
        Criteria criteria = new Criteria().where("_id").is(reviewId);
        Query query = new Query().addCriteria(criteria);
        Update updateOps = new Update();
        updateOps.push("edited").each(updatedReview);
        return mongoTemplate.updateFirst(query, updateOps, COLLECTION_REVIEWS);
    }



}
