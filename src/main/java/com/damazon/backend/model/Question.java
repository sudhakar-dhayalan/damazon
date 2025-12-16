package com.damazon.backend.model;

import lombok.Data;

@Data
public class Question {
    private int id;
    private String question;
    private String[] options;

    public Question(Integer id, String name, String[] options) {
        this.id = id;
        this.question = name;
        this.options = options;
    }
}
