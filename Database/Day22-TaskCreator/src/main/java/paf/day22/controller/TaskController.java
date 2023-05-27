package paf.day22.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import paf.day22.model.Task;
import paf.day22.repository.UserRepo;

@Controller
@RequestMapping(path = "task")
public class TaskController {

    @Autowired
    private UserRepo repo;

    @PostMapping
    public String addTask(@RequestBody MultiValueMap<String,String> postPayload,
    Model model){
        String username = postPayload.getFirst("username");
        String password = postPayload.getFirst("password");

        //check if user exist
        boolean userExist = repo.checkIfUserExist(username, password);
        if (userExist){
            String taskName = postPayload.getFirst("taskName");
            String priority = postPayload.getFirst("priority");
            String dateOfCompletion = postPayload.getFirst("dateOfCompletion");
    
            Task task = new Task();
            task.setUsername(username);
            task.setPassword(password);
            task.setTaskName(taskName);
            task.setPriority(priority);
            task.setDateOfCompletion(dateOfCompletion);

            //insert into task table
            repo.insertIntoTask(task);
            model.addAttribute("task", task);
            return "taskResult";
        }
        else{
            model.addAttribute("error", "wrong username or password");
            return "taskFailed";
        }


    }
    
}
