package com.example.kunal.studymanager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;

public class AddTask extends AppCompatActivity {

    EditText task_display_date;
    EditText task_display_time;
    EditText remainder_display_date;
    EditText remainder_display_time;
    // alram
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    private Date TaskDate, RemainderDate;

    Calendar REMAINDER_CALENDER_DATE = Calendar.getInstance();
    Calendar TASK_CALENDER_DATE = Calendar.getInstance();
    Calendar cal = Calendar.getInstance();
    DateFormat format_datetime = DateFormat.getDateInstance();
    private String TAG = "AddTask";


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

            TASK_CALENDER_DATE.set(year, monthOfYear, dayOfMonth);

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
            TASK_CALENDER_DATE.set(Calendar.HOUR_OF_DAY, hourOfDay);
            TASK_CALENDER_DATE.set(Calendar.MINUTE, minute);


        }
    };


    public void ReminderChooseDate(View view) {
        // new DatePickerDialog(AddTask.this, ReminderListenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
        DatePickerDialog dialog = new DatePickerDialog(AddTask.this, ReminderListenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

    }


    DatePickerDialog.OnDateSetListener ReminderListenerDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            remainder_display_date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            REMAINDER_CALENDER_DATE.clear();

            REMAINDER_CALENDER_DATE.set(year, monthOfYear, dayOfMonth);
/*
            REMAINDER_CALENDER_DATE.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            REMAINDER_CALENDER_DATE.set(Calendar.MONTH,monthOfYear);
            REMAINDER_CALENDER_DATE.set(Calendar.YEAR,year);
*/


        }
    };

    public void ReminderChooseTime(View view) {

        new TimePickerDialog(AddTask.this, ReminderListenerTime, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();

    }


    TimePickerDialog.OnTimeSetListener ReminderListenerTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String am_pm;


            REMAINDER_CALENDER_DATE.set(Calendar.HOUR_OF_DAY, hourOfDay);
            REMAINDER_CALENDER_DATE.set(Calendar.MINUTE, minute);


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
        Toast.makeText(AddTask.this, REMAINDER_CALENDER_DATE.getTime().toString(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onClickOk: " + REMAINDER_CALENDER_DATE.getTimeInMillis());
        Log.d(TAG, "onClickOk: " + REMAINDER_CALENDER_DATE.getTime().toString());


        if (isEmptyField(task_display_date) || isEmptyField(remainder_display_date) |
                isEmptyField(task_display_time) || isEmptyField(remainder_display_time)) {

            //setAlarm();
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, REMAINDER_CALENDER_DATE.getTimeInMillis(), alarmIntent);


            Toast.makeText(AddTask.this, "Reminder is set", Toast.LENGTH_SHORT).show();


        }


    }

    public void onClickCancel(View view) {
        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }

    private boolean isEmptyField(EditText editText) {
        if (editText.getText().toString().length() <= 0)
            return false;
        else
            return true;
    }

}
