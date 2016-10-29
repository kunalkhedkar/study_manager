package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kunal.ShowSubject;

import javax.security.auth.Subject;

public class AddSubject extends AppCompatActivity {
    DatabaseHelper mydb;

    EditText SubjectName, Chapters;
    private String subject_name;
    private int chapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        mydb = new DatabaseHelper(this);

        SubjectName = (EditText) findViewById(R.id.SubjectName);
        Chapters = (EditText) findViewById(R.id.Chapters);

    }


    public void onClickOk(View view) {
        subject_name = SubjectName.getText().toString();
        chapters = Integer.parseInt(Chapters.getText().toString());


        boolean isInserted=mydb.insertData_SUBJECT(subject_name,chapters);
        if(isInserted==false)
            Toast.makeText(AddSubject.this, "Fail to add subject : "+subject_name, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AddSubject.this,"Data Inserted", Toast.LENGTH_SHORT).show();


        Intent showSubjectIntent = new Intent(getApplicationContext(), ShowSubject.class);
        startActivity(showSubjectIntent);

    }


    public void onClickCancel(View view) {

        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
