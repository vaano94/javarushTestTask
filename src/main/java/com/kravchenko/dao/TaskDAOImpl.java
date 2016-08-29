package com.kravchenko.dao;

import com.kravchenko.entity.Task;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("taskDao")
public class TaskDAOImpl extends AbstractDAO implements TaskDAO {


    public void saveTask(Task task) {
        persist(task);
    }

    /**
     * Criteria method that receives all tasks
     * @return List of tasks
     */
    @SuppressWarnings("unchecked")
    public List<Task> getAllTasks() {
        Criteria criteria = getSession().createCriteria(Task.class);
        return (List<Task>)criteria.list();
    }

    /**
     * JPQL example to delete Task by Id
     * @param id id of the task to be deleted
     */
    /*public void deleteTaskById(int id) {
        Query q = getSession().createQuery("delete from task where id = :id ");
        q.setParameter("id", id);
        q.executeUpdate();
        //getSession().createQuery("delete " + Task.class.getName() + " where id=" + id).setParameter(1,id).executeUpdate();
    }*/

    public boolean deleteTaskById(Class<?> type, Serializable id) {
        Object persistentInstance = getSession().load(type, id);
        if (persistentInstance != null) {
            getSession().delete(persistentInstance);
            return true;
        }
        return false;
    }


    public Task getTaskById(long id) {
        return (Task) getSession().createCriteria(Task.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public List<Task> getTaskByStatus(String status) {
        return getSession().createCriteria(Task.class).add(Restrictions.eq("status", status)).list();
    }

    @Override
    public List<Task> getFilteredTasks(String filter) {
        switch (filter) {
            case "All":
            {
                return getAllTasks();
            }
            case "Uncompl":
            {
                return getSession().createCriteria(Task.class).add(Restrictions.eq("completed", false)).list();
            }
            case "Compl": {
                return getSession().createCriteria(Task.class).add(Restrictions.eq("completed", true)).list();
            }
            default: return getAllTasks();
        }
    }

    public void updateTask(Task task) {
        getSession().update(task);
    }
}
