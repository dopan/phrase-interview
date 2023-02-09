package com.phrase.ldopan.assignment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String parseProjects(JsonNode arrNode) {
        StringBuilder sb = new StringBuilder();
        if (arrNode.isArray()) {
            int i = 1;
            for (final JsonNode objNode : arrNode) {
                ObjectNode node = JsonNodeFactory.instance.objectNode();
                node.put("name", objNode.path("name"));
                node.put("status", objNode.path("status"));
                node.put("sourceLang", objNode.path("sourceLang"));
                node.put("targetLangs", objNode.path("targetLangs"));
                sb.append("PROJECT #" + i + ": " + node.toString().substring(1, node.toString().length() - 1) + "<br>");
                i++;
            }
        }
        return sb.toString();
    }
}
