package com.example.kunal.studymanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.Format;
import java.util.Calendar;

public class AddTask extends AppCompatActivity {

    EditText display_date;
    EditText display_time;
    Calendar cal = Calendar.getInstance();
    DateFormat format_datetime = DateFormat.getDateInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        display_date = (EditText) findViewById(R.id.DatePick);
        display_time = (EditText) findViewById(R.id.TimePick);

    }

    public void chooseDate(View view) {

        new DatePickerDialog(AddTask.this, listenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();

    }


    DatePickerDialog.OnDateSetListener listenerDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            display_date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
        }
    };

    public void chooseTime(View view) {

        new TimePickerDialog(AddTask.this, listenerTime, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();

    }


    TimePickerDialog.OnTimeSetListener listenerTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String am_pm;
            if (hourOfDay < 12)
                am_pm = "AM";
            else {
                am_pm = "PM";
                hourOfDay = hourOfDay - 12;
            }

            display_time.setText(hourOfDay + ":" + minute + " " + am_pm);

        }
    };
}
