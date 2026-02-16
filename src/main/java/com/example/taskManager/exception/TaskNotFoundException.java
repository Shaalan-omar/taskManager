package com.example.taskManager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id)
    {
        super("Task not found with this id: " + id);
    }
}
