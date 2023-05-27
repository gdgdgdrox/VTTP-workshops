package day22.rsvp;

public class Queries {
    public static final String SQL_GET_ALL_RSVP = "select * from respond;";
    public static final String SQL_GET_RSVP_BY_NAME = "select * from respond where name like ?;";
    public static final String SQL_UPSERT = """
        insert into respond (name, email, comments, phone, confirmation_date) values (?, ?, ?, ?, ?) 
        ON DUPLICATE KEY UPDATE
        name = ?, email = ?, comments = ?, phone = ?, confirmation_date = ?;
            """;
    public static final String SQL_GET_COUNT = "select count(*) as total from respond;";

}
