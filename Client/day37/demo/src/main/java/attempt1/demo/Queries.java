package attempt1.demo;

public class Queries {
    public static final String SQL_INSERT_POST = """
        insert into posts (post_id, picture, title, comment, comment2, comment3) values (?,?,?,?,?,?);
            """;
    
    public static final String SQL_GET_POST_IMAGE = """
        select picture from posts where post_id = ?;
        """;       

    public static final String SQL_GET_POST_INFO = """
            select title, comment, comment2, comment3 from posts where post_id = ?;
            """;
}
