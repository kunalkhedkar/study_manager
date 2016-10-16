package com.example.kunal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.kunal.studymanager.AddSubject;
import com.example.kunal.studymanager.R;
import com.example.kunal.studymanager.Subject;
import com.example.kunal.studymanager.SubjectManager;
import com.example.kunal.studymanager.Task;
import com.example.kunal.studymanager.TaskManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowSubject extends AppCompatActivity {

    FloatingActionButton fab;
    TextView tempDisplay;

    private String[] FROM = new String[]{"SubjectName", "Chpt"};
    private int[] TO = new int[]{R.id.SubjectName, R.id.chpt};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subject);
        setTitle("Subjects");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab_add);

        //init();

        ArrayList<HashMap<String, String>> data = populateData();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.subject_detail_list_view, FROM, TO);
        ListView marksList = (ListView) findViewById(R.id.subject_detail_list_view);
        marksList.setAdapter(simpleAdapter);
    }

    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String subject_name;
        int chpt;
        ArrayList<Subject> allSubjects = SubjectManager.getInstance().getAllSubjects();
        for (Subject sub : allSubjects) {
            subject_name = sub.getName();
            chpt = sub.getTotalChapters();


            HashMap<String, String> subjectDetail = new HashMap<>();
            subjectDetail.put("SubjectName", subject_name);
            subjectDetail.put("Chpt", chpt + "");
            detail.add(subjectDetail);

        }

        return detail;
    }

    private void init() {
        String sub_name;
        int chpt;

        ArrayList<Subject> allSubjects = SubjectManager.getInstance().getAllSubjects();

        for (Subject sub : allSubjects) {
            sub_name = sub.getName();
            chpt = sub.getTotalChapters();
            tempDisplay.append(sub_name + " : " + chpt + "\n");
        }
    }

    public void onClickAdd(View view) {

        Intent addSubjectIntent = new Intent(getApplicationContext(), AddSubject.class);
        startActivity(addSubjectIntent);

    }


}
