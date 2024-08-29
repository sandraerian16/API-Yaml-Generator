package com.example.apiyamlgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class ApiYamlGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiYamlGeneratorApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Create a YAML message converter
        MappingJackson2HttpMessageConverter yamlConverter = new MappingJackson2HttpMessageConverter(
                new ObjectMapper(new YAMLFactory())
        );
        yamlConverter.setSupportedMediaTypes(List.of(MediaType.parseMediaType("application/x-yaml")));

        // Add the YAML converter to RestTemplate
        restTemplate.getMessageConverters().add(yamlConverter);

        return restTemplate;
    }
}
