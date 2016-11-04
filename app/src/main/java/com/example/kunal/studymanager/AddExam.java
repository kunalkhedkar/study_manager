package com.example.kunal.studymanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddExam extends AppCompatActivity {
DatabaseHelper mydb;

    Calendar cal = Calendar.getInstance();
    EditText ExamDate, Subject, ExamTime, Title;
    private String exam_title, subject, str_exam_date, str_exam_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);
mydb=new DatabaseHelper(this);

        ExamDate = (EditText) findViewById(R.id.ExamDatePick);
        Subject = (EditText) findViewById(R.id.SubjectName);
        ExamTime = (EditText) findViewById(R.id.ExamTimePick);
        Title = (EditText) findViewById(R.id.ExamName);
    }

    public void ExamDate(View view) {

        DatePickerDialog dialog = new DatePickerDialog(AddExam.this, ExamListenerDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

    }


    DatePickerDialog.OnDateSetListener ExamListenerDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear=monthOfYear+1;
            str_exam_date = dayOfMonth + "/" + monthOfYear + "/" + year;
            ExamDate.setText(str_exam_date);
        }
    };

    public void ExamTime(View view) {

        new TimePickerDialog(AddExam.this, TaskListenerTime, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();

    }


    TimePickerDialog.OnTimeSetListener TaskListenerTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String am_pm;

            if (hourOfDay < 12)
                am_pm = "AM";
            else {
                am_pm = "PM";
                hourOfDay = hourOfDay - 12;
            }

            str_exam_time = hourOfDay + ":" + minute + " " + am_pm;
            ExamTime.setText(str_exam_time);

        }
    };


    public void onClickOk(View view) {
        exam_title = Title.getText().toString();
        subject = Subject.getText().toString();

        mydb.insertData_EXAM(exam_title, subject, str_exam_date,str_exam_time);

        Intent showExamIntent = new Intent(getApplicationContext(), ShowExam.class);
        startActivity(showExamIntent);

    }


    public void onClickCancel(View view) {

        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }

}
