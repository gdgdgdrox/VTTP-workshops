package boardgame.api.repository;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

//Day 26 
@Repository
public class CommentRepository {
    private static final String COLLECTION_COMMENTS = "comments";
    @Autowired
    private MongoTemplate mongoTemplate;

    public int getGameAverageRating(Integer gid){
        Criteria critera = Criteria.where("gid").is(gid);
        Query query = Query.query(critera);
        query.fields().include("rating").exclude("_id");
        List<Document> commentsDoc = mongoTemplate.find(query, Document.class, COLLECTION_COMMENTS);
        int numComments = commentsDoc.size();
        System.out.println("Number of comments for GID %d: %d".formatted(gid, numComments));
        int sumRating = commentsDoc.stream().map(commentDoc -> commentDoc.getInteger("rating"))
        .reduce(0, (a,b) -> a+b);
        int averageRating = sumRating / numComments;
        System.out.println("Average rating is %d".formatted(averageRating));
        return averageRating;
    }

}
