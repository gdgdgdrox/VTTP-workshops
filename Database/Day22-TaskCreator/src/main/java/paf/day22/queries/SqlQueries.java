package paf.day22.queries;

public class SqlQueries {
    public static final String SQL_CHECK_IF_USER_EXIST = 
    "select count(*) as exist from user where username=? AND password=sha(?)";

    //try select * and use rs.next = true or false
    public static final String SQL_CHECK_IF_USER_EXIST2 = 
    "select * from user where username=? AND password=sha(?)";

    public static final String SQL_INSERT_INTO_TASK = 
    "insert into task (task_name, priority, assign_to, completion_date) values (?,?,?,?)";
}
