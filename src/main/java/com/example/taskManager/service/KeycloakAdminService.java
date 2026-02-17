//
//package com.example.taskManager.service;
//
//
//import com.example.taskManager.KeycloakTokenService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class KeycloakAdminService {
//
//    private final WebClient webClient;
//    private final KeycloakTokenService keycloakTokenService;
//
//    @Value("${keycloak.base-url}") private String baseUrl;
//    @Value("${keycloak.realm}") private String realm;
//
//    public void clearRequiredActions(String keycloakUserId) {
//        String token = keycloakTokenService.getClientCredentialsToken()
//                .get("access_token").toString();
//
//        Map<String, Object> payload = Map.of("requiredActions", List.of());
//
//        webClient.put()
//                .uri(baseUrl + "/admin/realms/" + realm + "/users/" + keycloakUserId)
//                .headers(h -> h.setBearerAuth(token))
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(payload)
//                .retrieve()
//                .toBodilessEntity()
//                .block();
//    }
//}
//
////
////import org.keycloak.OAuth2Constants;
////import org.keycloak.admin.client.Keycloak;
////import org.keycloak.admin.client.KeycloakBuilder;
////import org.keycloak.representations.idm.UserRepresentation;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Service;
////
////import java.util.Collections;
////
////@Service
////public class KeycloakAdminService {
////
////    @Value("${keycloak.base-url}") private String baseUrl;
////    @Value("${keycloak.realm}") private String realm;
////
////    public void clearRequiredActions(String keycloakUserId, String callerAccessToken) {
////
////
////        Keycloak keycloak = KeycloakBuilder.builder()
////                .serverUrl(baseUrl)
////                .realm(realm)
////                .authorization(callerAccessToken)
////                .build();
////        var userResource = keycloak.realm(realm).users().get(keycloakUserId);
////        UserRepresentation user = userResource.toRepresentation();
////        user.setRequiredActions(Collections.emptyList());
////        userResource.update(user);
////        keycloak.close();
////    }
////}
