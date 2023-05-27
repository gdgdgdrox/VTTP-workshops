package day26.game.controller;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day26.game.model.BoardGame;
import day26.game.repository.CommentRepository;
import day26.game.service.BoardGameService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;

@RestController
//added CORS for Workshop 35
@CrossOrigin(origins = "*")
public class BoardGameController {
    
    @Autowired
    private BoardGameService service;

    @Autowired
    private CommentRepository commentRepo;
    
    @GetMapping(path = "/games")
    public ResponseEntity<String> returnAllBoardGames(@RequestParam(defaultValue="25") Integer limit, 
                                                    @RequestParam(defaultValue="0") Integer offset)
    {   
        //code for day 26 and 35 workshop
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (BoardGame bg : service.findAllBoardGames(limit, offset)){
            JsonObject obj = Json.createObjectBuilder().add("game_id", bg.getGameId()).add("name", bg.getName()).build();
            jsonArrayBuilder.add(obj);
        }
        JsonArray gamesArray = jsonArrayBuilder.build();
        
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        JsonObject responseBody = Json.createObjectBuilder().add("games", gamesArray)
                                                            .add("offset", offset)
                                                            .add("limit", limit)
                                                            .add("total", service.findTotalNumberOfGames())
                                                            .add("timestamp", timeStamp)
                                                            .build();
        ResponseEntity<String> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseBody.toString());
        return response;
    }

    @GetMapping(path = "/games/rank")
    public ResponseEntity<String> returnBoardGamesInAscOrder(@RequestParam(defaultValue="25") Integer limit, 
                                                    @RequestParam(defaultValue="0") Integer offset)
    {   
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (BoardGame bg : service.boardGamesAscOrder(limit, offset)){
            JsonObject obj = Json.createObjectBuilder().add("game_id", bg.getGameId()).add("name", bg.getName()).add("ranking", bg.getRanking()).build();
            jsonArrayBuilder.add(obj);
        }
        JsonArray gamesArray = jsonArrayBuilder.build();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        JsonObject responseBody = Json.createObjectBuilder().add("games", gamesArray)
                                                            .add("offset", offset)
                                                            .add("limit", limit)
                                                            .add("total", service.findTotalNumberOfGames())
                                                            .add("timestamp", timeStamp)
                                                            .build();
        ResponseEntity<String> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseBody.toString());
        return response;
    }

    @GetMapping(path="/game/{gameId}")
    public ResponseEntity<String> returnGameById(@PathVariable Integer gameId){
        Optional<Document> optDoc2 = service.findGameByIdReturnDoc(gameId);
        service.insertBG();
        return ResponseEntity.ok().body(optDoc2.get().toJson());
        // Optional<Document> optDoc = service.findGameById(gameId);
        // if (optDoc.isPresent()){
        //     Document d = optDoc.get();
        //     BoardGame bg = BoardGame.createBoardGame(d);
        //     JsonObjectBuilder objBuilder = BoardGame.createJsonObjectBuilder(bg);
        //     objBuilder.add("average_rating", Json.createValue(commentRepo.getGameAverageRating(gameId)));
        //     String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        //     objBuilder.add("timestamp",Json.createValue(timeStamp));
        //     JsonObject responseBody = objBuilder.build();
        //     ResponseEntity<String> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseBody.toString());
        //     return response;            
        // }
        // else{
        //     JsonObject responseBody = Json.createObjectBuilder().add("error", "unable to locate game_id " + gameId).build();
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody.toString());
        // }
    }
}
