package com.kravchenko;

import com.kravchenko.config.AppConfig;
import com.kravchenko.entity.Task;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.kravchenko.service.TaskService;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        TaskService taskService = (TaskService) ctx.getBean("taskService");

        /*Task task = new Task();
        task.setText("Hello world task");
        task.setStatus("All");

        taskService.saveTask(task);*/

        System.out.println(taskService.getAllTasks().toString());

        ctx.close();
    }

}
