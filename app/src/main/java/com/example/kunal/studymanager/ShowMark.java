package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowMark extends AppCompatActivity {

    FloatingActionButton fab;
    TextView tempDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mark);
        setTitle("Marks");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        tempDisplay = (TextView) findViewById(R.id.tempDisplay);

        init();
    }


    private void init() {
        String subject_name, test_name;
        int score_mark, total_mark;
        ArrayList<Mark> allmarks = MarkManager.getInstance().getAllMarks();

        for (Mark mk : allmarks) {
            test_name = mk.getTestName();
            subject_name = mk.getSUbjectName();
            score_mark = mk.getScoreMarksName();
            total_mark = mk.getTotalMark();
            tempDisplay.append(subject_name + " " + test_name + " " + score_mark + " " + total_mark + "\n");
        }
    }

    public void onClickAdd(View view) {

        Intent addMarkIntent = new Intent(getApplicationContext(), AddMark.class);
        startActivity(addMarkIntent);

    }
}
