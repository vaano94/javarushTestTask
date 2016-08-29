package com.kravchenko.entity;


import javax.persistence.*;

/**
 * Main entity of the application - Task.
 */
@Entity
@Table
public class Task {

    /**
     * Id
     */
    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Test of the task
     */
    @Column
    private String text;

    /**
     * Task status
     */
    @Column(length = 20)
    private String status;

    /**
     * Task status - can be completed or not.
     */
    @Column
    private boolean completed;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                ", completed=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (completed != task.completed) return false;
        if (!text.equals(task.text)) return false;
        return status.equals(task.status);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + text.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }
}
