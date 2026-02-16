package com.example.taskManager.service;

import com.example.taskManager.dto.CreateTaskRequest;
import com.example.taskManager.dto.TaskResponse;
import com.example.taskManager.dto.UpdateTaskRequest;
import com.example.taskManager.exception.TaskNotFoundException;
import com.example.taskManager.exception.UserNotFoundException;
import com.example.taskManager.model.Task;
import com.example.taskManager.model.User;
import com.example.taskManager.repository.TaskRepository;
import com.example.taskManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private TaskResponse toResponse(Task t) {
        return TaskResponse.builder()
                .id(t.getId())
                .title(t.getTitle())
                .description(t.getDescription())
                .status(t.getTaskStatus())
                .dueDate(t.getDueDate())
                .userId(t.getUser() != null ? t.getUser().getId() : null)
                .build();
    }

    public TaskResponse create(CreateTaskRequest dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));

        Task t = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .taskStatus(dto.getStatus())
                .dueDate(dto.getDueDate())
                .user(user)
                .build();

        return toResponse(taskRepository.save(t));
    }

    public Page<TaskResponse> listForUser(Long userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);

        return taskRepository.findByUserId(userId, pageable).map(this::toResponse);
    }

    public TaskResponse get(Long id) {
        Task t = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(t);
    }

    public TaskResponse update(Long id, UpdateTaskRequest dto) {
        Task t = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setTaskStatus(dto.getStatus());
        t.setDueDate(dto.getDueDate());

        return toResponse(taskRepository.save(t));
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) throw new TaskNotFoundException(id);
        taskRepository.deleteById(id);
    }
}
