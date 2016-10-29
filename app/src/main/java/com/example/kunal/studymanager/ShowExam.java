package com.example.kunal.studymanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowExam extends AppCompatActivity {
    DatabaseHelper mydb;

    FloatingActionButton fab;
    TextView tempDisplay;

    private String[] FROM = new String[]{"ExamName", "SubjectName", "Examdate", "ExamTime"};
    private int[] TO = new int[]{R.id.exam_name, R.id.subject_name, R.id.exam_date, R.id.exam_time};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exam);
        setTitle("Exams");
        mydb = new DatabaseHelper(this);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        fab = (FloatingActionButton) findViewById(R.id.fab_add);

        //    init();

        ArrayList<HashMap<String, String>> data = populateData();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.exam_detail_list_item, FROM, TO);
        ListView examList = (ListView) findViewById(R.id.exam_detail_list_view);
        examList.setAdapter(simpleAdapter);
    }

    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String exam_name, subject_name, exam_time, exam_date;
        Cursor result = mydb.getData_EXAM();

        if (result.getCount() == 0) {


        } else {
            while (result.moveToNext()) {
                exam_name = result.getString(0);
                subject_name = result.getString(1);
                exam_time = result.getString(2);
                exam_date = result.getString(3);

                HashMap<String, String> examDetail = new HashMap<>();
                examDetail.put("ExamName", exam_name);
                examDetail.put("SubjectName", subject_name);
                examDetail.put("Examdate", exam_date);
                examDetail.put("ExamTime", exam_time);

                detail.add(examDetail);

            }
        }
        return detail;
    }


    public void onClickAdd(View view) {

        Intent addExamIntent = new Intent(getApplicationContext(), AddExam.class);
        startActivity(addExamIntent);

    }
}
