package day26.game.repository;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import day26.game.model.BoardGame;

@Repository
public class BoardGameRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECTION_GAMES = "games";

    public long findTotalNumberOfGames(){
        long count = mongoTemplate.count(new Query(), COLLECTION_GAMES);
        return count;
    }
    //db.games.find({}).limit().offset()
    public List<Document> findAllBoardGames(int limit, int offset){
        Query query = new Query().limit(limit).skip(offset);
        List<Document> documents = mongoTemplate.find(query, Document.class, COLLECTION_GAMES);
        System.out.println("Retrieved " + documents.size() + " docs");
        return documents;
    }

    public List<BoardGame> findGamesInAscOrder(int limit, int offset){
        Query query = new Query().skip(offset).limit(limit).with(Sort.by(Sort.Direction.ASC, "ranking"));
        List<Document> documents = mongoTemplate.find(query, Document.class, COLLECTION_GAMES);
        List<BoardGame> boardGames = documents.stream().map(d -> (new BoardGame(d.getInteger("gid"), d.getString("name"), d.getInteger("ranking")))).toList();
        System.out.println("Retrieved " + documents.size() + " docs in ASC order" );
        return boardGames;
    }

    public Optional<Document> findGameById(Integer gameId){
        Query query = Query.query(Criteria.where("gid").is(gameId));
        Document doc = mongoTemplate.findOne(query, Document.class, COLLECTION_GAMES);
        System.out.println("DOC > " + doc);
        return Optional.ofNullable(doc);
    }

    public Optional<Document> findGameByIdReturnDoc(Integer gameId){
        Query query = Query.query(Criteria.where("gid").is(gameId));
        BoardGame bg = mongoTemplate.findOne(query, BoardGame.class, COLLECTION_GAMES);
        System.out.println(bg);
        Document doc = new Document();
        return Optional.ofNullable(doc);
    }

    public void insertBG(){
        BoardGame bg = new BoardGame();
        bg.setGameId(27017);
        bg.setName("GDawesomegame");
        bg.setYear(2022);
        bg.setAvailablePlatforms(List.of("pc","playstation"));
        System.out.println("BEFORE INSERTION");
        System.out.println(bg);
        BoardGame insertedObj = mongoTemplate.insert(bg,COLLECTION_GAMES);
        System.out.println("AFTER INSERTION");
        System.out.println(insertedObj);
    }
}
