package boardgame.api.controller;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boardgame.api.Utils;
import boardgame.api.model.Comment;
import boardgame.api.service.ReviewService;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@RestController
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewSvc;

    @PostMapping(path="/review", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //application/x-www-form-urlencoded is like a URL query string but with the query string encoded. 
    //because it is like a query string, we can use @RequestParam to get the query we want.
    public ResponseEntity<String> insertReview(@RequestBody MultiValueMap<String,String> payload, @RequestParam Integer gid){
        System.out.println("IN INSERT REVIEW CONTROLLER");
        System.out.println("PAYLOAD " + payload);
        System.out.println("Searching if gid %d exists".formatted(gid));
        List<Integer> gameIds = reviewSvc.getGameIds();
        if (!gameIds.contains(gid)){
            System.out.println("Oops! gid %d does not exist".formatted(gid));
            String error = Utils.createJsonObject("error","game id %d does not exist".formatted(gid)).toString();
            return ResponseEntity.status(400).body(error.toString());
        }
        String insertedId = reviewSvc.addReview(payload);
        String response = Utils.createJsonObject("success", "created review %s".formatted(insertedId)).toString();
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping(path="/review/{reviewId}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateReview(@RequestBody String payload, @PathVariable String reviewId){
        if (!reviewSvc.reviewExists(reviewId)){
            String response = Utils.createJsonObject("error", "unable to update review as review id %s does not exist".formatted(reviewId)).toString();
            return ResponseEntity.status(404).body(response);
        }
        boolean updateSuccess = reviewSvc.updateReview(payload, reviewId);
        if (!updateSuccess){
            String response = Utils.createJsonObject("failed", "update failed :(").toString();
            return ResponseEntity.internalServerError().body(response);
        }
        else{
            String response = Utils.createJsonObject("success", "review %s was successfully updated".formatted(reviewId)).toString();
            return ResponseEntity.ok(response);
        }
    }
}
