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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;

public class AddTask extends AppCompatActivity {

    CheckBox checkBox = null;


    EditText task_display_date, task_display_time, task_name;

    Spinner remainder_day;
    // alram
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    private Date TaskDate;
    private String str_task_date, str_task_time;


    Calendar TASK_CALENDAR_DATE = Calendar.getInstance();
    Calendar cal = Calendar.getInstance();
    DateFormat format_datetime = DateFormat.getDateInstance();
    private String TAG = "AddTask";
    private String str_choose_remainder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        task_display_date = (EditText) findViewById(R.id.TaskDatePick);
        task_display_time = (EditText) findViewById(R.id.TaskTimePick);
        task_name = (EditText) findViewById(R.id.taskName);

        remainder_day = (Spinner) findViewById(R.id.choose_remainder_day);
        String DAYS_LIST[] = {"1 hour", "3 hours", "8 hours", "12 hours", "1 day", "2 days", "3 days", "1 week", "2 weeks", "3 week", "1 month"};

        ArrayAdapter a = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, DAYS_LIST);
        remainder_day.setAdapter(a);


    }

    public void checkboxclickfun(View view) {

        checkBox = (CheckBox) findViewById(R.id.RemainderCheckbox);
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

            str_task_date = dayOfMonth + "/" + monthOfYear + "/" + year;
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

            if (hourOfDay < 12)
                am_pm = "AM";
            else {
                am_pm = "PM";
                hourOfDay = hourOfDay - 12;
            }
            str_task_time = hourOfDay + ":" + minute + " " + am_pm;
            task_display_time.setText(str_task_time);

            TASK_CALENDAR_DATE.set(Calendar.HOUR_OF_DAY, hourOfDay);
            TASK_CALENDAR_DATE.set(Calendar.MINUTE, minute);


        }
    };

    public void onClickOk(View view) {


        if (isEmptyField(task_display_date) || isEmptyField(task_display_time)) {

            str_choose_remainder = remainder_day.getSelectedItem().toString();
            Toast.makeText(AddTask.this, str_choose_remainder + " is selected", Toast.LENGTH_SHORT).show();
            //setAlarm();
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            //alarmManager.set(AlarmManager.RTC_WAKEUP, REMAINDER_CALENDAR_DATE.getTimeInMillis(), alarmIntent);

            Task task = new Task(task_name.getText().toString(), str_task_date, str_task_time);
            TaskManager.getInstance().addTask(task);

            Toast.makeText(AddTask.this, "Reminder is set", Toast.LENGTH_SHORT).show();
            Intent showTaskIntent = new Intent(getApplicationContext(), ShowTask.class);
            startActivity(showTaskIntent);

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
