package day22.rsvp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RsvpController {
    
    @Autowired
    private RsvpRepository repo;

    //Return all RSVPs
    @GetMapping("/api/rsvps")
    public ResponseEntity<String> getAllCustomers(){
        List<Rsvp> rsvps = repo.getAllRsvp();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Rsvp rsvp : rsvps){
            arrBuilder.add(Rsvp.toJson(rsvp));
        }
        JsonArray arr = arrBuilder.build();
        return ResponseEntity.ok().body(arr.toString());

    }

    //Search RSVP by name
    @GetMapping("/api/rsvp")
    public ResponseEntity<String> findCustomerByName(@RequestParam String name){
        Optional<List<Rsvp>> rsvps = repo.getRsvpsByName(name);
        if (rsvps.isEmpty()){
            JsonObject errorObject = Json.createObjectBuilder().add("error", "unable to find any rsvp with name: " + name).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorObject.toString());
        }
        else{
            List<Rsvp> rsvpList = rsvps.get();
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Rsvp r : rsvpList){
                arrBuilder.add(Rsvp.toJson(r));
            }
            JsonArray arr = arrBuilder.build();
            return ResponseEntity.ok().body(arr.toString());
        }
    }

    @PostMapping("/api/rsvp")
    public ResponseEntity<String> upsertRsvp(@RequestBody MultiValueMap<String,String> payload){
        System.out.println("SIZE" + payload.size());
        int result = repo.upsert(payload);
        if (result > 0){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/api/rsvps/count")
    public ResponseEntity<String> getCount(){
        return ResponseEntity.ok().body(repo.countRsvp().toString());
    }
}
