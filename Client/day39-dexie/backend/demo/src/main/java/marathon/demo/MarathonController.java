package marathon.demo;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@CrossOrigin(origins = "*")
public class MarathonController {

    @Autowired
    private MarathonRepository marathonRepo;

    // @PostMapping(path="api/sync", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> sync(@RequestBody String payload){
    //     StringReader sr = new StringReader(payload);
    //     JsonReader jr = Json.createReader(sr);
    //     JsonArray jsonArray = jr.readArray();
    //     List<Document> insertedDocs = marathonRepo.save(jsonArray);
    //     JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
    //     insertedDocs.forEach(doc -> 
    //         jsonArrayBuilder.add(docToJson(doc))
    //     );

    //     JsonArray responseBody = jsonArrayBuilder.build();
    //     responseBody.forEach(r -> System.out.println("response body" + r));
    //     return ResponseEntity.ok().body(responseBody.toString());
    // }

    // public static JsonObject docToJson(Document d){
    //     // System.out.println(d.get("_id").toString());
    //     return Json.createObjectBuilder().add("id", d.get("_id").toString()).add("email", d.getString("email")).build();
    // }

    @PostMapping(path="api/sync", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sync(@RequestBody String payload){

        marathonRepo.save2(payload);
        // JsonArray responseBody = jsonArrayBuilder.build();
        // responseBody.forEach(r -> System.out.println("response body" + r));
        // return ResponseEntity.ok().body(responseBody.toString());
        return null;
    }

    public static JsonObject docToJson(Document d){
        // System.out.println(d.get("_id").toString());
        return Json.createObjectBuilder().add("id", d.get("_id").toString()).add("email", d.getString("email")).build();
    }
}
