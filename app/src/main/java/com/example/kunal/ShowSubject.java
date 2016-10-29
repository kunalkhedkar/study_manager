package com.example.kunal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.kunal.studymanager.AddSubject;
import com.example.kunal.studymanager.DatabaseHelper;
import com.example.kunal.studymanager.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowSubject extends AppCompatActivity {
    DatabaseHelper mydb;

    FloatingActionButton fab;
    TextView tempDisplay;

    private String[] FROM = new String[]{"SubjectName", "Chpt"};
    private int[] TO = new int[]{R.id.SubjectName, R.id.chpt};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subject);
        setTitle("Subjects");
        mydb = new DatabaseHelper(this);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab_add);



        ArrayList<HashMap<String, String>> data = populateData();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.subject_detail_list_view, FROM, TO);
        ListView marksList = (ListView) findViewById(R.id.subject_detail_list_view);
        marksList.setAdapter(simpleAdapter);
    }

    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String subject_name;
        int chpt;

        //get data
        Cursor result = mydb.getData_SUBJECT();

        if(result.getCount()==0) {

        }
        else {
            while (result.moveToNext()) {
                subject_name = result.getString(0);
                chpt = Integer.parseInt(result.getString(1));

                HashMap<String, String> subjectDetail = new HashMap<>();
                subjectDetail.put("SubjectName", subject_name);
                subjectDetail.put("Chpt", chpt + "");
                detail.add(subjectDetail);
            }
        }

        return detail;
    }

    public void onClickAdd(View view) {

        Intent addSubjectIntent = new Intent(getApplicationContext(), AddSubject.class);
        startActivity(addSubjectIntent);

    }


}
