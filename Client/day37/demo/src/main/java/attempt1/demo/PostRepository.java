package attempt1.demo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.JsonObject;

@Repository
public class PostRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    //TODO: error handling
    public String savePost(MultipartFile image, JsonObject userPost) throws IOException{
        System.out.println("ATTEMPTING TO INSERT POST INTO DB");
        String postId = UUID.randomUUID().toString().substring(0,8);

        //we store the image as an inputstream of bytes
        InputStream imageInputStream = image.getInputStream();
        
        //insert into posts (post_id, picture, title, comment, comment2, comment3) values (?,?,?,?,?,?);
        jdbcTemplate.update(Queries.SQL_INSERT_POST,
                                        postId, 
                                        imageInputStream, 
                                        userPost.getString("title"),
                                        userPost.getString("comment"),
                                        userPost.getString("comment2"),
                                        userPost.getString("comment3"));
        return postId;
    }

    public Optional<Post> getPost(String postId){
        //retrieve info, excluding image
        System.out.println("RETRIEVING POST INFO...");
        SqlRowSet result = jdbcTemplate.queryForRowSet(Queries.SQL_GET_POST_INFO, postId);
        if (!result.next()){
            return Optional.empty();
        }
        //retrieve image blob
        System.out.println("RETRIEVING IMAGE FOR POST ID %s...".formatted(postId));
        List<byte[]> b = jdbcTemplate.query(Queries.SQL_GET_POST_IMAGE,
                                            (rs,n) -> rs.getBytes("picture"), postId);
        byte[] imageBytes = b.get(0);
        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

        //create Post object
        Post post = Post.createPost(imageBase64, result);
        return Optional.of(post);
    }

    
}
