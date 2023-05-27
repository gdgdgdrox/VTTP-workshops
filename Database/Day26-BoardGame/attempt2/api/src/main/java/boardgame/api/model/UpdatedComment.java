package boardgame.api.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdatedComment {
    private String comment;
    private Integer rating;
    private Timestamp date;
}
