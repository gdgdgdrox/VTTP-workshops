package day22.rsvp;

import java.util.Date;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rsvp {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String confirmationDate;
    private String comments;

    public static Rsvp createRsvp(Map<String,Object> r){
        Rsvp rsvp = new Rsvp();
        rsvp.setId((Integer)r.get("id"));
        rsvp.setName(((String)r.get("name")));
        rsvp.setEmail(((String)r.get("email")));
        rsvp.setPhone(((String)r.get("phone")));
        rsvp.setConfirmationDate( null == r.get("confirmation_date") ? "null" : r.get("confirmation_date").toString());
        rsvp.setComments(((String)r.get("comments")));  
        return rsvp;      
    }

    public static JsonObject toJson(Rsvp rsvp){
        return Json.createObjectBuilder()
                    .add("id", rsvp.getId())
                    .add("name", rsvp.getName())
                    .add("email", rsvp.getEmail())
                    .add("phone", rsvp.getPhone())
                    .add("date", null == rsvp.getConfirmationDate() ? "null" : rsvp.getConfirmationDate())
                    .add("comments", null == rsvp.getComments() ? "null" : rsvp.getComments())
                    .build();
    }

}
