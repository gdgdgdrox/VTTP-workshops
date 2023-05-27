package com.practice.boardgame;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/api/boardgame")
public class BoardGameController {
    private static final Logger logger = LoggerFactory.getLogger(BoardGameController.class);

    @Autowired
    private BoardGameService service;
    
    @PostMapping
    public ResponseEntity<String> createBoardGame(@RequestBody String payload){
        BoardGame bg = BoardGame.createBoardGame(payload);
        boolean save = service.saveBoardGame(bg);
        if (save){
            return (ResponseEntity.status(HttpStatus.CREATED).body("BoardGame (ID:" + bg.getId() + ") " + bg.getName() + " successfully created!"));
        }
        return (ResponseEntity.status(HttpStatus.CONFLICT)).body("BoardGame (ID: " + bg.getId() + ") " + bg.getName() + " already exists");
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getBoardGame(@PathVariable String id){
        Optional<BoardGame> optBG = service.findById(id);
        if (optBG.isPresent()){
            String responsePayload = optBG.get().toJsonObject().toString();
            return ResponseEntity.ok().body(responsePayload);
        }
        JsonObject jo = Json.createObjectBuilder().add("ERROR", "REQUESTED GAME ID: " + id + " does not exists.").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo.toString());
    }

    @GetMapping("/availablegames")
    public ResponseEntity<String> getAllAvailableGames() throws JsonProcessingException{
        List<BoardGame> boardGameList = service.getAllBoardGames();
        ObjectMapper mapper = new ObjectMapper();
        // mapper.writerWithDefaultPrettyPrinter().writeValueAsString(boardGameList);
        return ResponseEntity.ok(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(boardGameList));
    }
    
}
