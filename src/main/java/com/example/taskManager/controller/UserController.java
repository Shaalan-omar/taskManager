package com.example.taskManager.controller;


import com.example.taskManager.dto.UserRequest;
import com.example.taskManager.dto.UserResponse;
import com.example.taskManager.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService; //Hateet anna henna ba2a el @RequiredArgsConstructor 3ashan el error el tele3 dah

    @PreAuthorize("hasRole('TASK_WRITE')")
    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    @PreAuthorize("hasRole('TASK_READ')")
    @RolesAllowed("ADMIN")
    @GetMapping
    public ResponseEntity<List<UserResponse>> list() {
        return ResponseEntity.ok(userService.list());
    }

    @PreAuthorize("hasRole('TASK_READ')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PreAuthorize("hasRole('TASK_WRITE')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}