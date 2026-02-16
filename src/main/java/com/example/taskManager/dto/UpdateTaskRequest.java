package com.example.taskManager.dto;

import com.example.taskManager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTaskRequest {
    @NotBlank @Size(max = 200)
    private String title;

    @Size(max = 5000)
    private String description;

    @NotNull
    private TaskStatus status;

    private LocalDate dueDate;
}
