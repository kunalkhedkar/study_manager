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


    public void onClickSchedule(View view)
    {

    }

    public void oClickSubject(View view)
    {

    }

    public void onClickTask(View view)
    {

    }

    public void onClickExam(View view)
    {

    }

    public void onClickMark(View view)
    {
        Intent addMarkIntent= new Intent(getApplicationContext(),AddMark.class);
        startActivity(addMarkIntent);
    }
}
