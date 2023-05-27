package boardgame.api.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Game {
    @Field("_id")
    private String gameId;
    private String name;
    private Integer year;
    private Integer ranking;

    @Field("users_rated")
    private Integer usersRated;
    private String url;
    private String image;
    private List<String> genre;

    public Game(String gameId, String name){
        this.gameId = gameId;
        this.name = name;
    }

    public Game(){}
}
