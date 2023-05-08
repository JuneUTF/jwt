package com.example.demo.model;

import java.util.Map;

import lombok.Data;

@Data
public class EmailModel {
    private String to;
    private String subject;
    private String content;
    private Map<String, Object> props;
}
