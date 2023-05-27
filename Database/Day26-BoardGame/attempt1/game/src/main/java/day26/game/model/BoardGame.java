package day26.game.model;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardGame {
    private static Integer totalGames;

    private String id;

    @Field("gid")
    private Integer gameId;

    private String name;

    @Transient
    private Integer year;
    private Integer ranking;

    @Field("users_rated")
    private Integer usersRated;
    private String url;
    private String image;

    @Field("platforms")
    private List<String> availablePlatforms;

    @Field("locations.asia")
    private String location;

    public BoardGame(){}

    public BoardGame(Integer gameId, String name, Integer ranking){
        this.gameId = gameId;
        this.name = name;
        this.ranking = ranking;
    }

    public static BoardGame createBoardGame(Document doc){
        BoardGame bg = new BoardGame();
        bg.setGameId(doc.getInteger("gid"));
        bg.setName(doc.getString("name"));
        bg.setYear(doc.getInteger("year"));
        bg.setRanking(doc.getInteger("ranking"));
        bg.setUsersRated(doc.getInteger("users_rated"));
        bg.setUrl(doc.getString("url"));
        bg.setImage(doc.getString("image"));
        return bg;
    }

    public static JsonObjectBuilder createJsonObjectBuilder(BoardGame bg){
        return Json.createObjectBuilder().add("game_id", bg.getGameId())
                                        .add("name", bg.getName())
                                        .add("year", bg.getYear())
                                        .add("ranking", bg.getRanking())
                                        .add("users_rated", bg.getUsersRated())
                                        .add("url", bg.getUrl())
                                        .add("thumbnail", bg.getImage());
    }
}
