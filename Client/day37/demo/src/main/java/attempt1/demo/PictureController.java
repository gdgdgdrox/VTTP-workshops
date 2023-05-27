package attempt1.demo;
import java.io.StringReader;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Controller
public class PictureController {
    
    @Autowired
    private PostRepository postRepo;

    @PostMapping(path="/api/post", 
                consumes=MediaType.MULTIPART_FORM_DATA_VALUE,
                produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //we didnt need to enable multipart in application.properties to process multipart/form-data - check with Chuk
    public ResponseEntity<String> storePost(@RequestPart MultipartFile image, @RequestPart String userPost){
        System.out.println("IN POST CONTROLLER");
        JsonReader jsonReader = Json.createReader(new StringReader(userPost));
        JsonObject userPostJson = jsonReader.readObject();
        System.out.println("USER POST " + userPostJson);
        try{
            String postId = postRepo.savePost(image, userPostJson);
            //return the created post's id to the client. client can use this to make a GET request
            JsonObject responseBody = Utils.createResponseBody("post_id", postId);
            return ResponseEntity.status(201).body(responseBody.toString());
        }
        catch (Exception ex){
            JsonObject error = Utils.createResponseBody("error", ex.getMessage());
            return ResponseEntity.internalServerError().body(error.toString());

        }
    }
    

    @GetMapping(path="/api/post/{post_id}")
    @ResponseBody
    public ResponseEntity<String> returnImage(@PathVariable("post_id") String postId){
        System.out.println("IN GET CONTROLLER");
        Optional<Post> optionalPost = postRepo.getPost(postId);
        if (optionalPost.isEmpty()){
            JsonObject error = Utils.createResponseBody("error", "unable to locate post with id %d".formatted(postId));
            return ResponseEntity.status(404).body(error.toString());
        }
        Post post = optionalPost.get();
        JsonObject response = post.toJsonObject();
        return ResponseEntity.ok(response.toString());
    }

    


}
