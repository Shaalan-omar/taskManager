package com.example.taskManager.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakTokenService {

    private final WebClient webClient;

    @Value("${keycloak.base-url}") private String baseUrl;
    @Value("${keycloak.realm}") private String realm;

    @Value("${keycloak.client-id}") private String clientId;
    @Value("${keycloak.client-secret}") private String clientSecret;

    public Map<String, Object> getClientCredentialsToken(String username, String pass) {
        String body =
                "grant_type=client_credentials" +
                        "&client_id=" + url(clientId) +
                        "&client_secret=" + url(clientSecret);


               Keycloak keycloak = KeycloakBuilder.builder()
                       .serverUrl(baseUrl)
                       .realm(realm)
                       .clientId(clientId)
                       .clientSecret(clientSecret)
                       .username(username)
                       .password(pass)
                       .grantType(OAuth2Constants.PASSWORD) //ba2ool en I am using the password as the grant-type
                       .build();
        AccessTokenResponse token = keycloak.tokenManager().getAccessToken();
        keycloak.close();

        Map<String, Object> result = new HashMap<>();
        result.put("Access Token", token.getToken());
        result.put("Expires in", token.getExpiresIn());
        return result;



    }

    private static String url(String v) {
        return URLEncoder.encode(v, StandardCharsets.UTF_8);
    }
}
