package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMark extends AppCompatActivity {

    Spinner choose_subject;
    EditText testName, scoredMarks, outOfMarks;


    String TAG = "MainActivity";
    String str_testName = null;
    String str_choose_subject = null;
    int val_scoredMarks = 0;
    int val_outOfMarks = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark);


        choose_subject = (Spinner) findViewById(R.id.choose_subject);
        String SUBJECTLIST[] = {"JAVA", "DS", "UNP", "DAA", "PPL"};

        ArrayAdapter a = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, SUBJECTLIST);
        choose_subject.setAdapter(a);
        str_choose_subject = choose_subject.getSelectedItem().toString();

    }


    public void onClickOk(View view) {
        testName = (EditText) findViewById(R.id.test_name);
        scoredMarks = (EditText) findViewById(R.id.scoredMark);
        outOfMarks = (EditText) findViewById(R.id.outofMark);


        str_testName = testName.getText().toString();
        val_scoredMarks = Integer.parseInt(scoredMarks.getText().toString());
        val_outOfMarks = Integer.parseInt(outOfMarks.getText().toString());

        Log.d(TAG, "subject  :  " + str_choose_subject + "  scored marks  :  " + val_scoredMarks + " out of marks   :  " + val_outOfMarks);
        Toast.makeText(AddMark.this, "Data Accepted", Toast.LENGTH_SHORT).show();

    }


    public void onClickCancel(View view) {
        Toast.makeText(AddMark.this, "Cancel", Toast.LENGTH_SHORT).show();

        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }

}
