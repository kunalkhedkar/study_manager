package com.example.kunal.studymanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

    EditText task_display_date;
    EditText task_display_time;
    EditText remainder_display_date;
    EditText remainder_display_time;

    Calendar cal = Calendar.getInstance();
    DateFormat format_datetime = DateFormat.getDateInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        task_display_date = (EditText) findViewById(R.id.TaskDatePick);
        task_display_time = (EditText) findViewById(R.id.TaskTimePick);
        remainder_display_date = (EditText) findViewById(R.id.ReminderDatePick);
        remainder_display_time = (EditText) findViewById(R.id.ReminderTimePick);


    }

    public void TaskChooseDate(View view) {

        //new DatePickerDialog(AddTask.this, TasklistenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
        DatePickerDialog dialog = new DatePickerDialog(AddTask.this, TasklistenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

    }


    DatePickerDialog.OnDateSetListener TasklistenerDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            task_display_date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
        }
    };

    public void TaskChooseTime(View view) {

        new TimePickerDialog(AddTask.this, TasklistenerTime, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();

    }


    TimePickerDialog.OnTimeSetListener TasklistenerTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String am_pm;

            if (hourOfDay < 12)
                am_pm = "AM";
            else {
                am_pm = "PM";
                hourOfDay = hourOfDay - 12;
            }

            task_display_time.setText(hourOfDay + ":" + minute + " " + am_pm);

        }
    };


    public void ReminderChooseDate(View view) {
        // new DatePickerDialog(AddTask.this, ReminderlistenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
        DatePickerDialog dialog = new DatePickerDialog(AddTask.this, ReminderlistenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

    }


    DatePickerDialog.OnDateSetListener ReminderlistenerDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            remainder_display_date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

        }
    };

    public void ReminderChooseTime(View view) {

        new TimePickerDialog(AddTask.this, ReminderlistenerTime, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();

    }


    TimePickerDialog.OnTimeSetListener ReminderlistenerTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String am_pm;

            if (hourOfDay < 12)
                am_pm = "AM";
            else {
                am_pm = "PM";
                hourOfDay = hourOfDay - 12;
            }

            remainder_display_time.setText(hourOfDay + ":" + minute + " " + am_pm);

        }
    };


    public void onClickOk(View view) {
        Toast.makeText(AddTask.this, "done", Toast.LENGTH_SHORT).show();
    }

    public void onClickCancel(View view) {
        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }

}
