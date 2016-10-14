package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowExam extends AppCompatActivity {

    FloatingActionButton fab;
    TextView tempDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exam);
        setTitle("Marks");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        tempDisplay = (TextView) findViewById(R.id.tempDisplay);
        init();
    }


    private void init() {
        String exam_name, subject_name, exam_time, exam_date;
        ArrayList<Exam> allExams = ExamManager.getInstance().getAllExams();

        for (Exam ex : allExams) {
            exam_name = ex.getExamName();
            subject_name = ex.getSubjectName();
            exam_date = ex.getExamDate();
            exam_time = ex.getExamTime();

            tempDisplay.append(exam_name + " " + subject_name + " " + exam_date + " " + exam_time + "\n");
        }
    }

    public void onClickAdd(View view) {

        Intent addExamIntent = new Intent(getApplicationContext(), AddExam.class);
        startActivity(addExamIntent);

    }
}
