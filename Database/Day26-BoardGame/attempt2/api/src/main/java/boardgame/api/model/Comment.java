package boardgame.api.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
    private String user;
    private Integer rating;
    private String comment;
    private Integer gid;
    private String game;
    private Timestamp date;
}
