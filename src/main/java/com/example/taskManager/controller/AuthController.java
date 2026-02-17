package com.example.taskManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.taskManager.service.KeycloakTokenService;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final KeycloakTokenService keycloakTokenService;

    @PostMapping("/token")
    public ResponseEntity<Map<String, Object>> token(
            @RequestHeader("username") String username,
            @RequestHeader("password") String password
            //@Value("${internal.api-key}") String expected
    ) {
        return ResponseEntity.ok(keycloakTokenService.getClientCredentialsToken(username, password));
    }

}
