package com.kravchenko.service;

import com.kravchenko.dao.TaskDAO;
import com.kravchenko.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * This service manages interaction between controllers and DAO.
 * @Transactional here indicates that all methods are wrapped in transaction.
 */
@Service("taskService")
@Transactional
public class TaskService {

    /**
     * Injected TaskDAO
     */
    @Autowired
    private TaskDAO taskDAO;

    /**
     * Return all Tasks.
     * @return List of Tasks
     */
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    /**
     * Saves task in the database.
     */
    public void saveTask(Task task) {
        taskDAO.saveTask(task);
    }

    /**
     * Updates task in the database.
     */
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }
    /**
     * Deletes task from the database.
     */
    public void deleteTaskById(Class<?> clazz, Serializable id) {
        taskDAO.deleteTaskById(clazz, id);
    }

    /**
     * Returns list of filtered tasks by their 'completed' field
     * @param filter Field by which it can be filtered. Can be "All", "Uncompl" or "Compl"
     * @return List of tasks
     */
    public List<Task> getFilteredTasks(String filter) {
        return taskDAO.getFilteredTasks(filter);
    }

    /**
     * Returns task by a given id.
     * @param id ID of the Task
     * @return Task instance
     */
    public Task getTaskById(long id) {
        return taskDAO.getTaskById(id);
    }

    /**
     * Returns task by given status.
     * Not used anywhere now.
     * @param status String status.
     * @return List of tasks
     */
    public List<Task> getTaskByStatus(String status) {
        return taskDAO.getTaskByStatus(status);
    }


}
