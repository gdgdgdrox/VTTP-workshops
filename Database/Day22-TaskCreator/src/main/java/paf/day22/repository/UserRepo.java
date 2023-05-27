package paf.day22.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.day22.model.Task;
import paf.day22.queries.SqlQueries;

@Repository
public class UserRepo {
    
    @Autowired
    private JdbcTemplate template;

    public boolean checkIfUserExist(String username, String password){
        SqlRowSet result = template.queryForRowSet(SqlQueries.SQL_CHECK_IF_USER_EXIST,username,password);
        result.first();
        int count = result.getInt("exist");
        if (count == 1) return true;
        else return false;
    }

    public boolean insertIntoTask(Task task){
        int added = template.update(SqlQueries.SQL_INSERT_INTO_TASK, task.getTaskName(), task.getPriority(), task.getUsername(), task.getDateOfCompletion());
        return added > 0;
    }

    public boolean checkIfUserExist2(String username, String password){
        SqlRowSet result = template.queryForRowSet(SqlQueries.SQL_CHECK_IF_USER_EXIST2,username,password);
        if (result.next()){
            System.out.println("HAS NEXT");
            return true;
        }
        else {
            System.out.println("FALSE");
            return false;}
        // int count = result.getInt("exist");
        // if (count == 1) return true;
        // else return false;
    }
}
