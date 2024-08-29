package com.example.apiyamlgenerator.service;

import com.example.apiyamlgenerator.entity.YamlData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class YamlProcessorService {

    private final RestTemplate restTemplate;

    public YamlProcessorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String downloadYaml(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    public void processYaml(String yamlContent, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        YamlData yamlData = objectMapper.readValue(yamlContent, YamlData.class);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String yaml = mapper.writeValueAsString(yamlData);

        System.out.println(yaml);

        yaml = yaml.replace("---", "");
        String regex = "(?m)^[ \t]*\r?\n ";
        yaml = yaml.replaceAll(regex, "");


        Path path = Paths.get(filePath);
        String templateYaml = Files.readString(path);
        String[] lines = yaml.split("\n");
        StringBuilder modifiedContent = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i == 0) modifiedContent.append(lines[i]).append("\n");
            modifiedContent.append("    ").append(lines[i]).append("\n");
        }

        String processedYaml = templateYaml.replace("<openapi>", modifiedContent);

        Files.writeString(Paths.get("newAPI.yaml"), processedYaml);
    }
}
