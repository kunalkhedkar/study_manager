package com.example.kunal.studymanager;

/**
 * Created by kunal on 8/10/16.
 */
public class Subject {
    private String name;
    private int totalChapters;

    public Subject(String name, int totalChapters) {
        this.name = name;
        this.totalChapters = totalChapters;
    }

    public int getTotalChapters() {
        return this.totalChapters;
    }

    public String getName() {
        return this.name;
    }
}
