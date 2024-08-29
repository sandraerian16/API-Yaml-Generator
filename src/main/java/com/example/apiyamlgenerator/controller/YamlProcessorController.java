package com.example.apiyamlgenerator.controller;

import com.example.apiyamlgenerator.entity.YamlProcessingRequest;
import com.example.apiyamlgenerator.service.YamlProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/yaml")
public class YamlProcessorController {

    @Autowired
    private YamlProcessorService yamlProcessorService;

    @PostMapping("/process")
    public String processYamlFile(@RequestBody YamlProcessingRequest request) {
        try {
            String yamlContent = yamlProcessorService.downloadYaml(request.getServiceUrl());

            yamlProcessorService.processYaml(yamlContent, request.getTemplatePath());

            return "YAML processing successful!";
        } catch (IOException e) {
            e.printStackTrace();
            return "YAML processing failed: " + e.getMessage();
        }
    }
}
