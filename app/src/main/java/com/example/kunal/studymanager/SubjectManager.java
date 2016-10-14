package com.example.kunal.studymanager;

import java.util.ArrayList;

/**
 * Created by kunal on 8/10/16.
 */
public class SubjectManager {

    private static SubjectManager singleton = null;


    private ArrayList<Subject> subjects = new ArrayList<>();

    private SubjectManager() {
    }

    public static SubjectManager getInstance() {
        if (singleton == null) {
            singleton = new SubjectManager();
        }
        return singleton;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public ArrayList<Subject> getAllSubjects() {
        return this.subjects;
    }

}
