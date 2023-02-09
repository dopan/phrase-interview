package com.phrase.ldopan.assignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RESTService {

    private final String LOGIN_URL;
    private final String PROJECTS_URL;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.phrase.login.username}")
    private String username;
    @Value("${rest.phrase.login.password}")
    private String password;

    public RESTService(@Value("${rest.phrase.login.url}") String loginUrl,
                       @Value("${rest.phrase.projects.url}") String projectsUrl) {
        this.LOGIN_URL = loginUrl;
        this.PROJECTS_URL = projectsUrl;
    }

    public String login() throws JsonProcessingException {
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("userName", username);
        jsonRequest.put("password", password);
        jsonRequest.put("code", "string");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>(jsonRequest.toString(), headers);
        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity(LOGIN_URL, request, String.class);
        JsonNode root = objectMapper.readTree(responseEntityStr.getBody());
        return root.path("token").asText();
    }

    public JsonNode loadProjects(String authToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "ApiToken " + authToken);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                PROJECTS_URL,
                HttpMethod.GET,
                request,
                String.class
        );
        return objectMapper.readTree(response.getBody());
    }
}
