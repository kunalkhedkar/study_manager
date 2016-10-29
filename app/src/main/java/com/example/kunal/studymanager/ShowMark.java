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

import java.util.ArrayList;
import java.util.HashMap;

public class ShowMark extends AppCompatActivity {
    DatabaseHelper mydb;

    FloatingActionButton fab;
    TextView tempDisplay;

    private String[] FROM = new String[]{"ExamName", "SubjectName", "ScoreMakrs", "TotalMarks"};
    private int[] TO = new int[]{R.id.exam_name, R.id.subject_name, R.id.scored_marks, R.id.total_marks};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mark);
        setTitle("Marks");
        mydb=new DatabaseHelper(this);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        fab = (FloatingActionButton) findViewById(R.id.fab_add);


//        init();

        ArrayList<HashMap<String, String>> data = populateData();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.marks_detail_list_item, FROM, TO);
        ListView marksList = (ListView) findViewById(R.id.marks_detail_list_view);
        marksList.setAdapter(simpleAdapter);

    }

    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String exam_name, subject_name;
        int score_marks, total_marks;

        Cursor result = mydb.getData_MARKS();

        if (result.getCount() == 0) {


        } else {
            while (result.moveToNext()) {
                exam_name = result.getString(0);
                subject_name = result.getString(1);
                score_marks = Integer.parseInt(result.getString(2));
                total_marks = Integer.parseInt(result.getString(3));


                HashMap<String, String> marksDetail = new HashMap<>();
                marksDetail.put("ExamName", exam_name);
                marksDetail.put("SubjectName", subject_name);
                marksDetail.put("ScoreMakrs", score_marks + "");
                marksDetail.put("TotalMarks", total_marks + "");

                detail.add(marksDetail);

            }
        }

        return detail;
    }


    public void onClickAdd(View view) {

        Intent addMarkIntent = new Intent(getApplicationContext(), AddMark.class);
        startActivity(addMarkIntent);

    }
}
