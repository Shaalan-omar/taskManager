package com.example.taskManager.repository;

import com.example.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailIgnoreCase(String email);
    List<User> findByNameContainingIgnoreCase(Optional<String> name);
    List<User> findByEmailContainingIgnoreCase(Optional<String> email);
}
