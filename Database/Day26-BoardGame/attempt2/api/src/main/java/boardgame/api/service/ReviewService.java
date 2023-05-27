package boardgame.api.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mongodb.client.result.UpdateResult;

import boardgame.api.Utils;
import boardgame.api.repository.GameRepository;
import boardgame.api.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    public List<Integer> getGameIds(){
        return gameRepo.getGameIds();
    }

    public String addReview(MultiValueMap<String,String> payload){
        Document d = Utils.createDocument(payload);
        return reviewRepo.addReview(d);
        // return reviewRepo.addReview(payload);
    }

    public boolean reviewExists (String reviewId){
        boolean exists = reviewRepo.reviewExists(reviewId);
        System.out.println("Review %s exists %b".formatted(reviewId, exists));
        return reviewRepo.reviewExists(reviewId);
    }

    public boolean updateReview(String payload, String reviewId){
        Document d = Document.parse(payload);
        d.put("posted", new Timestamp(System.currentTimeMillis()));
        UpdateResult updateResult = reviewRepo.updateReview(d, reviewId);
        if (updateResult.getModifiedCount() == 1) return true;
        else return false;
    }
}
