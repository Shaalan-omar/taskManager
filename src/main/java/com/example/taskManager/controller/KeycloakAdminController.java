//package com.example.taskManager.controller;
//
//import com.example.taskManager.service.KeycloakAdminService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/admin/keycloak")
//public class KeycloakAdminController {
//
//    private final KeycloakAdminService keycloakAdminService;
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/users/{keycloakUserId}/clear-required-actions")
//    public ResponseEntity<Void> clearRequiredActions(
//            @PathVariable String keycloakUserId,
//            @org.springframework.security.core.annotation.AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt
//    ) {
//        keycloakAdminService.clearRequiredActions(keycloakUserId);
//        return ResponseEntity.noContent().build();
//    }
//}
