package org.testtask.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testtask.model.dto.LoginResponse;
import org.testtask.model.dto.StoreDto;
import org.testtask.model.dto.StoreResponse;

import java.util.List;

@Service
public class RestApiCallerService implements RestApiCaller {
    @Value("${api.login.path}")
    private String loginApiPath;
    @Value("${api.stores.path}")
    private String storesApiPath;
    @Value("${api.key}")
    private String apiKey;
    @Value("${api.client.id}")
    private String clientId;
    @Value("${api.client.secret}")
    private String clientSecret;
    @Value("${api.grant.type}")
    private String grantType;
    private String token;
    @Autowired
    private RestTemplate restTemplate;

    public String getBearerToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", apiKey);

        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("client_id", clientId);
        values.add("client_secret", clientSecret);
        values.add("grant_type", grantType);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(values, headers);
        ResponseEntity<LoginResponse> response = restTemplate
                .exchange(loginApiPath, HttpMethod.POST, entity, LoginResponse.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            LoginResponse result = response.getBody();
            if (result != null) {
                String accessToken = response.getBody().access_token();
                this.token = accessToken;
                return accessToken;
            } else {
                throw new IllegalStateException("Response body is empty");
            }
        } else {
            throw new IllegalStateException(
                    "Wrong Response status: " + response.getStatusCode() +
                    ", expected: " + HttpStatus.OK);
        }
    }

    public List<StoreDto> getStoresList() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", apiKey);
        if (StringUtils.isEmpty(token)) {
            token = getBearerToken();
        }
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<StoreResponse> response = restTemplate
                .exchange(storesApiPath, HttpMethod.GET, entity, StoreResponse.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StoreResponse result = response.getBody();
            if (result != null) {
                return result.items();
            } else {
                throw new IllegalStateException("Response body is empty");
            }
        } else {
            throw new IllegalStateException(
                    "Wrong Response status: " + response.getStatusCode() +
                    ", expected: " + HttpStatus.OK);
        }
    }
}