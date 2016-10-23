package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SchedulerNext extends AppCompatActivity {
    private int no_of_sub_day, repetition, totalSubjects, EstimatedScheduleDays, revisionDays;
    private long daysDiff;
    int Count_Arr_Days[]=new int[totalSubjects];

    ArrayList<Subject> allSubjects = SubjectManager.getInstance().getAllSubjects();

    TextView textViewsub1, textViewsub2, textViewsub3, textViewsub4, textViewsub5, textViewsub6, textViewsub7, textViewsub8;
    Button sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_next);

        getPreviousActivityData();
        designMyView();
    }

    private void designMyView() {

        for (int i = 1; i <= totalSubjects; i++) {
            String SubjectName = allSubjects.get(i - 1).getName();
            TurnVisibilityON(i, SubjectName);
        }

    }

    private void TurnVisibilityON(int i, String SubjectName) {
        int day=0;
        String BTname = "sub" + Integer.toString(i);
        String TEXTname = "TextSub" + Integer.toString(i);

        int btid = getResources().getIdentifier(BTname, "id", getApplicationContext().getPackageName());
        Button bt = (Button) findViewById(btid);

        int txtid = getResources().getIdentifier(TEXTname, "id", getApplicationContext().getPackageName());
        TextView tx = (TextView) findViewById(txtid);

        day=Count_Arr_Days[i-1];
        bt.setText(Integer.toString(day));
        bt.setVisibility(View.VISIBLE);

        tx.setText(SubjectName);
        tx.setVisibility(View.VISIBLE);
    }


    private void getPreviousActivityData() {
        Bundle bundle = getIntent().getExtras();

        no_of_sub_day = bundle.getInt("no_of_sub_day");
        repetition = bundle.getInt("repetition");
        totalSubjects = bundle.getInt("totalSubjects");
        daysDiff = bundle.getLong("daysDiff");
        revisionDays = bundle.getInt("revisionDays");
        Count_Arr_Days= getIntent().getIntArrayExtra("Count_array");



    }

    public void addSchedule(View view) {
        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
