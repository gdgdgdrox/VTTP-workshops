package attempt1.demo;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Utils {
    
    public static JsonObject createResponseBody(String key, String message){
        return Json.createObjectBuilder().add(key, message).build();
    }
}
