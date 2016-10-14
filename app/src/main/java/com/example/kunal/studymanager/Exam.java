package com.example.kunal.studymanager;

/**
 * Created by kunal on 9/10/16.
 */
public class Exam {

    private String ExamName, SubjectName, ExamDate, ExamTime;


    public Exam(String exam_name, String subject_name, String exam_date, String exam_time) {
        this.ExamName = exam_name;
        this.SubjectName = subject_name;
        this.ExamDate = exam_date;
        this.ExamTime = exam_time;
    }

    public String getExamName() {
        return this.ExamName;
    }

    public String getSubjectName() {
        return this.SubjectName;
    }

    public String getExamDate() {
        return this.ExamDate;
    }

    public String getExamTime() {
        return this.ExamTime;
    }

}
