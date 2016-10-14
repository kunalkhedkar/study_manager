package com.example.kunal.studymanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ScheduleData extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    Calendar TARGET_CALENDAR_DATE = Calendar.getInstance();
    Spinner NoOfSubDay, Repetition, TotalSubjects;
    EditText TargetDate, revisionDays;

    private String NUMBERS[] = {"1", "2", "3"};
    private String SUB_NUMBERS[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_data);

        Repetition = (Spinner) findViewById(R.id.Repetition);
        NoOfSubDay = (Spinner) findViewById(R.id.NoOfSubDay);
        TargetDate = (EditText) findViewById(R.id.TargetDate);
        revisionDays = (EditText) findViewById(R.id.RevisionDays);
        TotalSubjects = (Spinner) findViewById(R.id.TotalSubject);

        ArrayAdapter a = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, NUMBERS);
        NoOfSubDay.setAdapter(a);


        ArrayAdapter b = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, NUMBERS);
        Repetition.setAdapter(b);

        ArrayAdapter c = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, SUB_NUMBERS);
        TotalSubjects.setAdapter(c);

    }

    public void ChooseTargetDate(View view) {

        DatePickerDialog dialog = new DatePickerDialog(ScheduleData.this, TargetListenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

    }


    DatePickerDialog.OnDateSetListener TargetListenerDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            TargetDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            TARGET_CALENDAR_DATE.clear();
            TARGET_CALENDAR_DATE.set(year, monthOfYear, dayOfMonth);
        }
    };


    public void onClickNext(View view) {

        int no_of_sub_day, repetition, totalSubjects, revDays;
        Calendar today = Calendar.getInstance();
    /*   long diff =(System.currentTimeMillis()-1000) - TARGET_CALENDAR_DATE.getTimeInMillis();
        long day=diff/(24*60*60*1000);
        day=day*-1;

        long end = TARGET_CALENDAR_DATE.getTimeInMillis();
        long start = System.currentTimeMillis();
        long day=TimeUnit.MILLISECONDS.toDays(Math.abs(end-start));
        if(day>0)
            day=day+1;


        int diff = TARGET_CALENDAR_DATE.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
*/

        long end = TARGET_CALENDAR_DATE.getTimeInMillis();
        long start = System.currentTimeMillis() - 1000;
        long msDiff = end - start;

        long daysDiff = (TimeUnit.MILLISECONDS.toDays(msDiff)) + 1;

        Toast.makeText(ScheduleData.this, daysDiff + " days are selected for schedule", Toast.LENGTH_SHORT).show();


        no_of_sub_day = Integer.parseInt(NoOfSubDay.getSelectedItem().toString());
        repetition = Integer.parseInt(Repetition.getSelectedItem().toString());
        totalSubjects = Integer.parseInt(TotalSubjects.getSelectedItem().toString());
        revDays = Integer.parseInt(revisionDays.getText().toString());

        Intent addMultipleSubjectIntent = new Intent(getApplicationContext(), AddMultipleSubject.class);
        addMultipleSubjectIntent.putExtra("daysDiff", daysDiff);
        addMultipleSubjectIntent.putExtra("totalSubjects", totalSubjects);
        addMultipleSubjectIntent.putExtra("no_of_sub_day", no_of_sub_day);
        addMultipleSubjectIntent.putExtra("repetition", repetition);
        addMultipleSubjectIntent.putExtra("revisionDays", revDays);

        startActivity(addMultipleSubjectIntent);


    }
}
