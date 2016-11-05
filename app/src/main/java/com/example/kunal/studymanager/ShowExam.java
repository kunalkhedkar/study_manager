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
    SimpleAdapter simpleAdapter;
    ListView examList;

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

        build_listview();

        ArrayList<HashMap<String, String>> data = populateData();

        simpleAdapter = new SimpleAdapter(this, data, R.layout.exam_detail_list_item, FROM, TO);
        examList = (ListView) findViewById(R.id.exam_detail_list_view);
        examList.setAdapter(simpleAdapter);
    }

    private void build_listview() {

        ArrayList<HashMap<String, String>> data = populateData();

        simpleAdapter = new SimpleAdapter(this, data, R.layout.exam_detail_list_item, FROM, TO);
        examList = (ListView) findViewById(R.id.exam_detail_list_view);
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

    public void delete_exam(View view){

        View parentRow = (View) view.getParent();
        final int position = examList.getPositionForView(parentRow);

        HashMap<String, Object> obj = (HashMap<String, Object>) examList.getAdapter().getItem(position);
        String ExamName = (String) obj.get("ExamName");
        String SubjectName = (String) obj.get("SubjectName");
        String Examdate = (String) obj.get("Examdate");
        String ExamTime = (String) obj.get("ExamTime");

        if(mydb.delete_exam(ExamName,SubjectName,Examdate,ExamTime)){
            build_listview();
            Toast.makeText(ShowExam.this,ExamName+ " details deleted ", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(ShowExam.this, "Fail to delete Record", Toast.LENGTH_SHORT).show();


    }
}
