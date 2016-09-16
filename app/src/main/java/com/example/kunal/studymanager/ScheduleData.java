package com.example.kunal.studymanager;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class ScheduleData extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    Spinner NoOfSubDay, Repetition;
    EditText TargetDate;
    private int no_of_sub_day, repetition;
    private String NUMBERS[] = {"1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_data);

        Repetition = (Spinner) findViewById(R.id.Repetition);
        NoOfSubDay = (Spinner) findViewById(R.id.NoOfSubDay);
        TargetDate = (EditText) findViewById(R.id.TargetDate);


        ArrayAdapter a = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, NUMBERS);
        NoOfSubDay.setAdapter(a);
        no_of_sub_day = Integer.parseInt(NoOfSubDay.getSelectedItem().toString());


        ArrayAdapter b = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, NUMBERS);
        Repetition.setAdapter(b);
        repetition = Integer.parseInt(Repetition.getSelectedItem().toString());
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
        }
    };


    public void onClickNext(View view) {
        Toast.makeText(ScheduleData.this, "OK", Toast.LENGTH_SHORT).show();
    }
}
