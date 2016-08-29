package com.kravchenko.web;

import com.kravchenko.entity.Task;
import com.kravchenko.service.TaskService;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for updating task values.
 */
@RestController
public class UpdateController {


    /**
     * Injected taskService
     */
    @Autowired
    TaskService taskService;

    /**
     * Updates tasks sent by client.
     * Checks if they've been updated, if new ones were created or if they were deleted.
     * @param data String representation of request
     * @return Returns empty string
     */
    @RequestMapping(value = "/updateProgress", method = RequestMethod.POST)
    public String updateProgress(@RequestBody String data) {

        try {
            JSONArray jsonArray = new JSONArray(data);

            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);

                // delete task if status is Deleted
                if (object.getString("status").equals("deleted")) {
                    taskService.deleteTaskById(Task.class , new Long(object.getInt("id")));
                }

                // if task status changed
                if (object.has("id")) {
                    Task t = taskService.getTaskById(object.getInt("id"));
                    if (t.isCompleted() != object.getBoolean("completed")) {
                        t.setCompleted(object.getBoolean("completed"));
                        taskService.updateTask(t);
                    }
                }

                // if it is a new task
                if (object.getString("status").equals("new")) {
                    Task task = new Task();
                    task.setStatus("all");
                    task.setCompleted(false);
                    String text = object.getString("text");
                    task.setText(text);
                    taskService.saveTask(task);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
