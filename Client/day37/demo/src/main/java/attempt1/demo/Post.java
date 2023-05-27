package attempt1.demo;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Post {
    private String postId;
    private String image;
    private String title;
    private String comment;
    private String comment2;
    private String comment3;

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment2() {
        return this.comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return this.comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    @Override
    public String toString() {
        return "{" +
            " image='" + getImage() + "'" +
            ", title='" + getTitle() + "'" +
            ", comment='" + getComment() + "'" +
            ", comment2='" + getComment2() + "'" +
            ", comment3='" + getComment3() + "'" +
            "}";
    }

    public JsonObject toJsonObject(){
        return Json.createObjectBuilder().add("image", this.getImage())
                                    .add("title", this.getTitle())
                                    .add("comment", this.getComment())
                                    .add("comment2", this.getComment2())
                                    .add("comment3", this.getComment3())
                                    .build();
    }

    public static Post createPost(String image, SqlRowSet rs){
        Post post = new Post();
        post.setImage(image);
        post.setTitle(rs.getString("title"));
        post.setComment(rs.getString("comment"));
        post.setComment2(rs.getString("comment2"));
        post.setComment3(rs.getString("comment3"));
        return post;
    }

}
