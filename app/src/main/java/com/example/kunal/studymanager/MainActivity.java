package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kunal.ShowSubject;

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
        Intent showSubjectIntent = new Intent(getApplicationContext(), ShowSubject.class);
        startActivity(showSubjectIntent);
    }

    public void onClickTask(View view) {

        Intent showTaskIntent = new Intent(getApplicationContext(), ShowTask.class);
        startActivity(showTaskIntent);


    }

    public void onclickExam(View view) {
        Intent showExamIntent = new Intent(getApplicationContext(), ShowExam.class);
        startActivity(showExamIntent);

    }

    public void onClickMark(View view) {
        Intent showMarkIntent = new Intent(getApplicationContext(), ShowMark.class);
        startActivity(showMarkIntent);
    }

    public void onClickshow(View view) {
        Intent showScheduleIntent = new Intent(getApplicationContext(), ShowSchedule.class);
        startActivity(showScheduleIntent);
    }
}
