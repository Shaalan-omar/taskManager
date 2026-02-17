package com.example.taskManager;

import com.example.taskManager.KeycloakAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/keycloak")
public class KeycloakAdminController {

    private final KeycloakAdminService keycloakAdminService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/{keycloakUserId}/clear-required-actions")
    public ResponseEntity<Void> clearRequiredActions(
            @PathVariable String keycloakUserId,
            @org.springframework.security.core.annotation.AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt
    ) {
        keycloakAdminService.clearRequiredActions(keycloakUserId, jwt.getTokenValue());
        return ResponseEntity.noContent().build();
    }
}
