package com.practice.boardgame;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardGame implements Serializable{
    private Integer id;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;

    public static BoardGame createBoardGame(String json){
        String modifiedString = json.replace("\\", "");
        StringReader strReader = new StringReader(modifiedString);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        return createBoardGame(jsonObject);
    }

    public static BoardGame createBoardGame(JsonObject jsonObject){
        BoardGame boardGame = new BoardGame();
        boardGame.setId(jsonObject.getInt("id"));
        boardGame.setName(jsonObject.getString("name"));
        boardGame.setYear(jsonObject.getInt("year"));
        boardGame.setRanking(jsonObject.getInt("ranking"));
        boardGame.setUsersRated(jsonObject.getInt("users_rated"));
        boardGame.setUrl(jsonObject.getString("url"));
        boardGame.setImage(jsonObject.getString("image"));
        return boardGame;
    }

    public JsonObject toJsonObject(){
        return Json.createObjectBuilder()
        .add("id", this.getId())
        .add("name", this.getName())
        .add("year", this.getYear())
        .add("ranking", this.getRanking())
        .add("users_rated", this.getUsersRated())
        .add("url", this.getUrl())
        .add("image", this.getImage())
        .build();
    }
    
}