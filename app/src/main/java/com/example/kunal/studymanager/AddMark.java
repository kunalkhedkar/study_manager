package com.example.kunal.studymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMark extends AppCompatActivity {

    Spinner choose_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark);


        choose_subject= (Spinner) findViewById(R.id.choose_subject);
        String SUBJECTLIST []={"JAVA","DS","UNP","DAA","PPL"};

        ArrayAdapter a=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,SUBJECTLIST);
        choose_subject.setAdapter(a);
        String text = choose_subject.getSelectedItem().toString();
        //Toast.makeText(AddMark.this,text, Toast.LENGTH_SHORT).show();
    }



}
