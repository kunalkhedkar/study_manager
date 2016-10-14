package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddMultipleSubject extends AppCompatActivity {
    private int no_of_sub_day, repetition, totalSubjects, revisionDays;
    private long daysDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_multiple_subject);
        setTitle("Add Subjects");

        getPreviousActivityData();

        designMyView();

    }


    private void designMyView() {

        for (int i = 1; i <= totalSubjects; i++) {
            TurnVisibilityON(i);
        }

    }


    private void TurnVisibilityON(int i) {

        String EDITsub = "subject" + Integer.toString(i);
        String EDITchpt = "chpt" + Integer.toString(i);

        int subid = getResources().getIdentifier(EDITsub, "id", getApplicationContext().getPackageName());
        EditText subed = (EditText) findViewById(subid);

        int chptid = getResources().getIdentifier(EDITchpt, "id", getApplicationContext().getPackageName());
        EditText chpted = (EditText) findViewById(chptid);

        subed.setVisibility(View.VISIBLE);
        chpted.setVisibility(View.VISIBLE);
    }


    private void getPreviousActivityData() {
        Bundle bundle = getIntent().getExtras();

        no_of_sub_day = bundle.getInt("no_of_sub_day");
        repetition = bundle.getInt("repetition");
        totalSubjects = bundle.getInt("totalSubjects");
        daysDiff = bundle.getLong("daysDiff");
        revisionDays = bundle.getInt("revisionDays");
    }


    public void onClickOk(View view) {


        getDataInput();


        Intent scheduleNext = new Intent(getApplicationContext(), SchedulerNext.class);
        scheduleNext.putExtra("daysDiff", daysDiff);
        scheduleNext.putExtra("totalSubjects", totalSubjects);
        scheduleNext.putExtra("no_of_sub_day", no_of_sub_day);
        scheduleNext.putExtra("repetition", repetition);
        scheduleNext.putExtra("revisionDays", revisionDays);

        startActivity(scheduleNext);
    }

    private void getDataInput() {

        for (int i = 1; i <= totalSubjects; i++) {

            String EDITsub = "subject" + Integer.toString(i);
            String EDITchpt = "chpt" + Integer.toString(i);

            int subid = getResources().getIdentifier(EDITsub, "id", getApplicationContext().getPackageName());
            EditText subed = (EditText) findViewById(subid);
            String sub_name = subed.getText().toString();

            int chptid = getResources().getIdentifier(EDITchpt, "id", getApplicationContext().getPackageName());
            EditText chpted = (EditText) findViewById(chptid);
            int chpt = Integer.parseInt(chpted.getText().toString());

            Subject sub = new Subject(sub_name, chpt);
            SubjectManager.getInstance().addSubject(sub);

        }
    }


}
