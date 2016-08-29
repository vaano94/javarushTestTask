package com.kravchenko.web;

import com.kravchenko.entity.Task;
import com.kravchenko.service.TaskService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;

/**
 * Main controller in the application.
 */
@RestController
public class TaskController {

    /**
     * Injected taskService
     */
    @Autowired
    TaskService taskService;


    /**
     * Returns list of all Tasks.
     * @return List Tasks
     */
    @RequestMapping("/getAll")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Returns filtered tasks.
     * @param filter String completed value
     * @return List Tasks
     */
    @RequestMapping("/filter/{filter}")
    public @ResponseBody List<Task> getFilteredTasks(@PathVariable String filter ) {
        System.out.println("received filter :" + filter);
        return taskService.getFilteredTasks(filter);
    }


}
