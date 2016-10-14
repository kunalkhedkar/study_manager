package com.example.kunal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kunal.studymanager.AddSubject;
import com.example.kunal.studymanager.R;
import com.example.kunal.studymanager.Subject;
import com.example.kunal.studymanager.SubjectManager;

import java.util.ArrayList;

public class ShowSubject extends AppCompatActivity {

    FloatingActionButton fab;
    TextView tempDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subject);
        setTitle("Subjects");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        tempDisplay = (TextView) findViewById(R.id.tempDisplay);

        init();
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
