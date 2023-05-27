package boardgame.api.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boardgame.api.model.Game;
import boardgame.api.repository.CommentRepository;
import boardgame.api.repository.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private CommentRepository commentRepo;
    /*
     * games: [ <array of games> ],
        offset: <offset or default value>,
        limit: <limit or default value>,
        total: <total number of games>,
        timestamp: <result timestamp>
     */
    public Document getAllGames(int limit, int skip){
        Document doc = new Document();
        List<Game> games = gameRepo.getAllGames(limit, skip);
        // List<Game> mappedGames = games.stream().map(game -> new Game(game.getGameId(), game.getName())).toList();
        List<Game> mappedGames = games.stream().flatMap(game -> Stream.of(new Game(game.getGameId(), game.getName()))).toList();

        long count = gameRepo.countGames();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        doc.put("games", mappedGames);
        doc.put("offset", skip);
        doc.put("limit", limit);
        doc.put("total", count);
        doc.put("timestamp", timestamp);
        return doc;
    }

    /*
     * {
        game_id: <ID field>,
        name: <Name field>,
        year: <Year field>,
        ranking: <Rank field>,
        average: <Average field>,
        users_rated: <Users rated field>,
        url: <URL field>,
        thumbnail: <Thumbnail field>,
        timestamp: <result timestamp>
        }
     */
    public Document getGameById(String objId){
        Optional<Document> optionalGame = gameRepo.getGameById(objId);
        if (optionalGame.isEmpty()){
            return null;
        }
        Document game = optionalGame.get();
        Integer gid = game.getInteger("gid");
        int avgRating = commentRepo.getGameAverageRating(gid);
        game.put("average", avgRating);
        game.put("timestamp", new Timestamp(System.currentTimeMillis()));
        System.out.println("GAME : " + game);
        return game;
    }
}
