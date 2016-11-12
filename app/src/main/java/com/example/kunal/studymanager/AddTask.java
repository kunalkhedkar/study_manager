package com.example.kunal.studymanager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTask extends AppCompatActivity {
    DatabaseHelper mydb;

    CheckBox checkBox = null;


    EditText task_display_date, task_display_time, task_name;

    Spinner remainder_day;
    // alram
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    //private Date TaskDate;
    private String str_task_date, str_task_time;


    Calendar TASK_CALENDAR_DATE = Calendar.getInstance();
    Calendar cal = Calendar.getInstance();
    DateFormat format_datetime = DateFormat.getDateInstance();
    private String str_choose_remainder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DatabaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        task_display_date = (EditText) findViewById(R.id.TaskDatePick);
        task_display_time = (EditText) findViewById(R.id.TaskTimePick);
        task_name = (EditText) findViewById(R.id.taskName);

        remainder_day = (Spinner) findViewById(R.id.choose_remainder_day);
        String DAYS_LIST[] = {"1 hour", "3 hours", "8 hours", "12 hours", "1 day", "2 days", "3 days", "1 week"};

        ArrayAdapter a = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, DAYS_LIST);
        remainder_day.setAdapter(a);
        checkBox = (CheckBox) findViewById(R.id.RemainderCheckbox);


    }

    public void checkboxclickfun(View view) {


        if (checkBox.isChecked()) {
            remainder_day.setVisibility(View.VISIBLE);
        } else {
            remainder_day.setVisibility(View.INVISIBLE);
            str_choose_remainder = null;
        }

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

            str_task_date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            task_display_date.setText(str_task_date);


            TASK_CALENDAR_DATE.set(year, monthOfYear, dayOfMonth);

        }
    };

    public void TaskChooseTime(View view) {

        new TimePickerDialog(AddTask.this, TasklistenerTime, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();

    }


    TimePickerDialog.OnTimeSetListener TasklistenerTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String am_pm;
            int hour = hourOfDay;
            if (hourOfDay < 12) {
                TASK_CALENDAR_DATE.set(Calendar.AM_PM, Calendar.AM);
                am_pm = "AM";
            } else {
                am_pm = "PM";
                cal.set(Calendar.AM_PM, Calendar.PM);
                hourOfDay = hourOfDay - 12;
            }
            str_task_time = hourOfDay + ":" + minute + " " + am_pm;
            task_display_time.setText(str_task_time);

            TASK_CALENDAR_DATE.set(Calendar.HOUR_OF_DAY, hour);
            TASK_CALENDAR_DATE.set(Calendar.MINUTE, minute);
            TASK_CALENDAR_DATE.set(Calendar.SECOND, 0);
            TASK_CALENDAR_DATE.set(Calendar.MILLISECOND, 0);


        }
    };

    public void onClickOk(View view) {


        if (isNotEmptyField(task_display_date) || isNotEmptyField(task_display_time) || isNotEmptyField(task_name)) {

            if (checkBox.isChecked()) {
                set_Remaindar();
            }

            boolean isInserted = mydb.insertData_TASK(task_name.getText().toString(), str_task_date, str_task_time);
            if (!isInserted)
                Toast.makeText(AddTask.this, "Fail to add Exam", Toast.LENGTH_SHORT).show();


            Intent showTaskIntent = new Intent(getApplicationContext(), ShowTask.class);
            startActivity(showTaskIntent);

        } else
            Toast.makeText(AddTask.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();


    }

    void set_Remaindar() {
        str_choose_remainder = remainder_day.getSelectedItem().toString();
        //setAlarm();
/*
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);

        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, TASK_CALENDAR_DATE.getTimeInMillis(), alarmIntent);
*/

        long time = TASK_CALENDAR_DATE.getTimeInMillis();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 30);
        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
        intent.putExtra("taskname", task_name.getText().toString());

        final int _id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,_id, intent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);


        Toast.makeText(AddTask.this, "Reminder is set", Toast.LENGTH_SHORT).show();
    }

    public void onClickCancel(View view) {
        Intent mainActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(mainActivityIntent);
    }

    public boolean isNotEmptyField(EditText editText) {
        if (editText.getText().toString().length() <= 0)
            return false;
        else
            return true;

    }

}
