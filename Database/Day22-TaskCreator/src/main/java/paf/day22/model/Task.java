package paf.day22.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Task {
    private String username;
    private String password;
    private String taskName;
    private String priority;
    private String dateOfCompletion;

}
