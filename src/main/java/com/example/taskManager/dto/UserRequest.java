package com.example.taskManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


//We set rules here using Jakarta rules to be able to take requests within the desired format.
@Data
public class UserRequest {
    @NotBlank @Size(max = 100)
    private String name;

    @NotBlank @Email @Size(max = 100)
    private String email;
}
