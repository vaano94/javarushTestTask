package com.kravchenko.dao;

import com.kravchenko.entity.Task;

import java.io.Serializable;
import java.util.List;

/**
 * This class serves as interface for DAO implementations.
 */
public interface TaskDAO {

    /**
     * Saves task.
     * @param task Task
     */
    void saveTask(Task task);

    /**
     * Receives allTasks.
     * @return List<Task> list
     */
    List getAllTasks();

    /**
     * Deletes task by given id
     * @param type Class instance
     * @param id instance id
     * @return indicates wheter Id was deleted or not
     */
    boolean deleteTaskById(Class<?> type, Serializable id);

    /**
     * Return task instance.
     * @param id Task id
     * @return Task instance
     */
    Task getTaskById(long id);

    /**
     * Returns list by a given status
     * @param status Status
     * @return List<Task>
     */
    List<Task> getTaskByStatus(String status);

    /**
     * Updates tasks.
     * @param task Task
     */
    void updateTask(Task task);

    /**
     * Returns List of filtered tasks by 'completed' field.
     * @param filter Should be "all", "uncompl" or "compl"
     * @return List of Tasks
     */
    List<Task> getFilteredTasks(String filter);
}
