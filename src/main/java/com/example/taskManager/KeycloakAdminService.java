package com.example.taskManager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;@Service
@RequiredArgsConstructor
public class KeycloakAdminService {

    private final WebClient webClient;

    @Value("${keycloak.base-url}") private String baseUrl;
    @Value("${keycloak.realm}") private String realm;

    public void clearRequiredActions(String keycloakUserId, String callerAccessToken) {
        Map<String, Object> payload = Map.of("requiredActions", List.of());

        webClient.put()
                .uri(baseUrl + "/admin/realms/" + realm + "/users/" + keycloakUserId)
                .headers(h -> h.setBearerAuth(callerAccessToken))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
