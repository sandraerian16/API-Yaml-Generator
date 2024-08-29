package com.example.apiyamlgenerator.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PathItem {
    @JsonProperty("get")
    @JsonAlias({"post", "delete", "put"})
    private Operation operation;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Operation {
        private List<Parameter> parameters;
        private Map<String, Response> responses;


    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Parameter {
        private String name;
        private String in;
        private boolean required;
        private Schema schema;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Schema {
        private String type;


    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Response {
        private String description;
        private Map<String, MediaType> content;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class MediaType {
        private Schema schema;


    }

}

