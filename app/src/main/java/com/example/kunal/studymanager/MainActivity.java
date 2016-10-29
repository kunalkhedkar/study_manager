package com.example.kunal.studymanager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kunal.ShowSubject;

public class MainActivity extends AppCompatActivity {
DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);


        //b1.setBackgroundColor(Color.parseColor("#01ff90"));
    }


    public void onClickSchedule(View view) {

        Cursor result=mydb.getData_SCHEDULE();
        if(result.getCount()==0) {
            Intent scheduleData = new Intent(getApplicationContext(), ScheduleData.class);
            startActivity(scheduleData);
        }
        else
        {
            Intent showScheduleIntent = new Intent(getApplicationContext(), ShowSchedule.class);
            startActivity(showScheduleIntent);
        }

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
