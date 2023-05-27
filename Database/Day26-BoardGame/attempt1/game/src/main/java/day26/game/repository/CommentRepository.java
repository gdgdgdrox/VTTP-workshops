package day26.game.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    // db.comments.find({gid:?})
    public Integer getGameAverageRating(Integer gameId){
        Query query = Query.query(Criteria.where("gid").is(gameId));
        //can my entity class be Comment.class?
        List<Document> documents =mongoTemplate.find(query, Document.class, "comments");
        Integer avgRating = (documents.stream().map(doc -> doc.getInteger("rating")).reduce(0, (subtotal, element) -> subtotal + element)/documents.size());
        System.out.println("AVERAGE RATING " + avgRating);
        return avgRating;
    }
}
