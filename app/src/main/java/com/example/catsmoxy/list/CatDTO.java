package com.example.catsmoxy.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatDTO {

    private String id;
    private String url;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
