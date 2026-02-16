package com.example.taskManager.controller;

import com.example.taskManager.dto.CreateTaskRequest;
import com.example.taskManager.dto.TaskResponse;
import com.example.taskManager.dto.UpdateTaskRequest;
import com.example.taskManager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("hasRole('TASK_WRITE')")
    @PostMapping("/api/tasks")
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(dto));
    }
    @PreAuthorize("hasRole('TASK_READ')")
    @GetMapping("/api/users/{userId}/tasks")
    public ResponseEntity<?> listForUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(taskService.listForUser(userId, pageable));
    }

    @PreAuthorize("hasRole('TASK_READ')")
    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<TaskResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.get(id));
    }

    @PreAuthorize("hasRole('TASK_WRITE')")
    @PutMapping("/api/tasks/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest dto) {
        return ResponseEntity.ok(taskService.update(id, dto));
    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
