package com.phrase.ldopan.assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.phrase.ldopan.assignment.service.ProjectService;
import com.phrase.ldopan.assignment.service.RESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ProjectController {

    @Autowired
    RESTService restService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public String config() {
        return "projects";
    }

    @GetMapping("/fetchProjects")
    public ResponseEntity fetchProjects() {
        String token;
        JsonNode json = null;
        try {
            token = restService.login();
            json = restService.loadProjects(token);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot load projects from Phrase!");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.parseProjects(json.get("content")));
    }
}
