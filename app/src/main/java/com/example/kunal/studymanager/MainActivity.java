package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickSchedule(View view) {
        Intent scheduleData = new Intent(getApplicationContext(), ScheduleData.class);
        startActivity(scheduleData);

    }

    public void onclickSubject(View view) {
        Intent addSubjectIntent = new Intent(getApplicationContext(), AddSubject.class);
        startActivity(addSubjectIntent);
    }

    public void onClickTask(View view) {

        Intent addTaskIntent = new Intent(getApplicationContext(), AddTask.class);
        startActivity(addTaskIntent);


    }

    public void onclickExam(View view) {
        Intent addExamIntent = new Intent(getApplicationContext(), AddExam.class);
        startActivity(addExamIntent);

    }

    public void onClickMark(View view) {
        Intent addMarkIntent = new Intent(getApplicationContext(), AddMark.class);
        startActivity(addMarkIntent);
    }
}
