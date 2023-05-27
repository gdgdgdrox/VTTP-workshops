package boardgame.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import boardgame.api.model.Game;

@Repository
public class GameRepository {
    private static final String COLLECTION_GAMES = "games";
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> getAllGames(int limit, int skip){
        Query query = new Query().limit(limit).skip(skip);
        return mongoTemplate.find(query, Game.class, COLLECTION_GAMES);
    }

    public long countGames(){
        return mongoTemplate.count(new Query(),COLLECTION_GAMES);
    }
    
    public Optional<Document> getGameById(String objId){
        ObjectId objectId = new ObjectId(objId);
        Document game = mongoTemplate.findById(objectId, Document.class, COLLECTION_GAMES);
        return Optional.ofNullable(game);
    }

    // public Integer getGameID(String objId){
    //     Document doc = mongoTemplate.findById(new ObjectId(objId), Document.class, COLLECTION_GAMES);
    //     return doc.getInteger("gid");
    // }

    public void insertGame(String payload) {
        Document game = Document.parse(payload);
        mongoTemplate.insert(game, "fakegames");
    }

    public List<Integer> getGameIds(){
        List<Integer> gameIds = mongoTemplate.findDistinct(new Query(), "gid", COLLECTION_GAMES, Integer.class);
        return gameIds;
    }

    // public List<Document> getGameRatings(Stri)
    //TO DO - use both application/json and application/x-www-form-urlencoded as content-type for post request.
    //for each content-type, map the request to String / MVM and try to parse into Document for insertion.

}

