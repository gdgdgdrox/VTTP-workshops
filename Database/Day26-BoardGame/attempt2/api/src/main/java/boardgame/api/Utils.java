package boardgame.api;

import java.sql.Timestamp;

import org.bson.Document;
import org.springframework.util.MultiValueMap;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Utils {

    public static JsonObject createJsonObject(String key, String message){
        return Json.createObjectBuilder().add(key,message).build();
    }

    public static Document createDocument(MultiValueMap<String,String> payload){
        /*
         *     private String user;
                private Integer rating;
                private String comment;
                private Integer gid;
                private String gameName;
                private Timestamp date;
         */
        Document d = new Document();
        d.put("user", payload.getFirst("user"));
        d.put("rating", Integer.parseInt(payload.getFirst("rating")));
        d.put("comment", payload.getFirst("comment"));
        d.put("gid", Integer.parseInt(payload.getFirst("gid")));
        d.put("game", payload.getFirst("game"));
        d.put("date", new Timestamp(System.currentTimeMillis()));
        System.out.println(d);
        return d;
    }
}