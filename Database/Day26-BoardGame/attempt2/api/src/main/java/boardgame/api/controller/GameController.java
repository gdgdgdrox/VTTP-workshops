package boardgame.api.controller;

import java.util.Map;
import java.util.Optional;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;

import boardgame.api.model.Game;
import boardgame.api.repository.GameRepository;
import boardgame.api.service.GameService;

@RestController
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private GameService gameSvc;

    @Autowired
    private ObjectMapper objMapper;
    
    //Task 1
    @GetMapping(path="/games")
    public ResponseEntity<String> getAllGames(@RequestParam(defaultValue="25") Integer limit, @RequestParam(defaultValue="0") Integer skip){
        System.out.println("IN GET ALL GAMES CONTROLLER. LIMIT %s, SKIP %s".formatted(limit, skip));
        Document response = gameSvc.getAllGames(limit, skip);
        try {
            String responseBody = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            return ResponseEntity.ok(responseBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    //Task 3
    @GetMapping(path="/game/{game_id}")
    public ResponseEntity<String> getGameById(@PathVariable("game_id") String objId){
        System.out.println("IN CONTROLLER");
        System.out.println("GAME_ID: " + objId);

        Document game = gameSvc.getGameById(objId);
        if (game.isEmpty()){
            return ResponseEntity.status(404).body("not found");
        }
        try {
            String responseBody = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(game);
            return ResponseEntity.ok(responseBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        
    }

}
 