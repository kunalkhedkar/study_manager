package com.example.kunal.studymanager;

import java.util.ArrayList;

/**
 * Created by kunal on 9/10/16.
 */
public class ExamManager {

    private static ExamManager singleton = null;


    private ArrayList<Exam> exams = new ArrayList<>();

    private ExamManager() {
    }

    public static ExamManager getInstance() {
        if (singleton == null) {
            singleton = new ExamManager();
        }
        return singleton;
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    public ArrayList<Exam> getAllExams() {
        return this.exams;
    }
}
