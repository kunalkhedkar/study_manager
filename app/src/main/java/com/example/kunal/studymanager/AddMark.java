package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMark extends AppCompatActivity {
    DatabaseHelper mydb;


    EditText testName, scoredMarks, outOfMarks,choose_subject;


    String TAG = "MainActivity";
    String str_testName = null;
    String str_choose_subject = null;
    int val_scoredMarks = 0;
    int val_outOfMarks = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark);
        mydb=new DatabaseHelper(this);
    }


    public void onClickOk(View view) {
        testName = (EditText) findViewById(R.id.test_name);
        scoredMarks = (EditText) findViewById(R.id.scoredMark);
        outOfMarks = (EditText) findViewById(R.id.outofMark);
        choose_subject = (EditText) findViewById(R.id.choose_subject);

        str_testName = testName.getText().toString();
        val_scoredMarks = Integer.parseInt(scoredMarks.getText().toString());
        val_outOfMarks = Integer.parseInt(outOfMarks.getText().toString());
        str_choose_subject=choose_subject.getText().toString();

        mydb.insertData_MARKS(str_choose_subject, str_testName, val_scoredMarks, val_outOfMarks);

        Intent showMarkIntent = new Intent(getApplicationContext(), ShowMark.class);
        startActivity(showMarkIntent);
    }


    public void onClickCancel(View view) {
        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }

}
