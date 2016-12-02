package com.example.kunal.studymanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SchedulerNext extends AppCompatActivity {
    DatabaseHelper mydb;
    private int no_of_sub_day, repetition, totalSubjects, EstimatedScheduleDays, revisionDays;
    private long daysDiff;
    int Count_Arr_Days[] = new int[totalSubjects];

    ArrayList<String> allSubjects = new ArrayList<>();

    TextView textViewsub1, textViewsub2, textViewsub3, textViewsub4, textViewsub5, textViewsub6, textViewsub7, textViewsub8;
    Button sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(mainActivityIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_next);
        mydb = new DatabaseHelper(this);

        fillSubjectArrayList();
        getScheduleData();
        designMyView();
    }

    private void fillSubjectArrayList() {

        Cursor result = mydb.getData_SUBJECT();
        if (result.getCount() == 0) {

        } else {
            while (result.moveToNext())
                allSubjects.add(result.getString(0));
        }
    }

    private void designMyView() {

        for (int i = 1; i <= totalSubjects; i++) {
            String SubjectName = allSubjects.get(i - 1);
            TurnVisibilityON(i, SubjectName);
        }

    }

    private void TurnVisibilityON(int i, String SubjectName) {
        int day = 0;
        String BTname = "sub" + Integer.toString(i);
        String TEXTname = "TextSub" + Integer.toString(i);

        int btid = getResources().getIdentifier(BTname, "id", getApplicationContext().getPackageName());
        Button bt = (Button) findViewById(btid);

        int txtid = getResources().getIdentifier(TEXTname, "id", getApplicationContext().getPackageName());
        TextView tx = (TextView) findViewById(txtid);

        day = Count_Arr_Days[i - 1];
        bt.setText(Integer.toString(day));
        bt.setVisibility(View.VISIBLE);

        tx.setText(SubjectName);
        tx.setVisibility(View.VISIBLE);
    }


    private void getScheduleData() {

        Bundle bundle = getIntent().getExtras();

        Cursor result = mydb.getData_SCHEDULE();
        if (result.getCount() == 0) {
            Toast.makeText(SchedulerNext.this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            while (result.moveToNext()) {

                daysDiff = Long.parseLong(result.getString(2));
                totalSubjects = Integer.parseInt(result.getString(3));
                no_of_sub_day = Integer.parseInt(result.getString(4));
                repetition = Integer.parseInt(result.getString(5));
                revisionDays = Integer.parseInt(result.getString(6));
                revisionDays = bundle.getInt("revisionDays");
                Count_Arr_Days = getIntent().getIntArrayExtra("Count_array");
            }
        }
    }

        public void addSchedule (View view){
            Intent showScheduleIntent = new Intent(getApplicationContext(), ShowSchedule.class);
            startActivity(showScheduleIntent);
        }
    }
