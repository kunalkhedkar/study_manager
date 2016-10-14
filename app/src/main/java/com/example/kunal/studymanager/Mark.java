package com.example.kunal.studymanager;

/**
 * Created by kunal on 9/10/16.
 */
public class Mark {
    private String TestName, SubjectName;
    private int ScoreMarks, TotalMark;

    public Mark(String subject_name, String test_name, int score_mark, int total_mark) {
        this.TestName = test_name;
        this.SubjectName = subject_name;
        this.ScoreMarks = score_mark;
        this.TotalMark = total_mark;

    }

    public String getTestName() {
        return this.TestName;
    }

    public String getSUbjectName() {
        return this.SubjectName;
    }

    public int getScoreMarksName() {
        return this.ScoreMarks;
    }

    public int getTotalMark() {
        return this.TotalMark;
    }
}
