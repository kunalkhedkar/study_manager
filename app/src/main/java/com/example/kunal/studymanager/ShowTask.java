package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowTask extends AppCompatActivity {

    FloatingActionButton fab;
    TextView tempDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        setTitle("Task");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        tempDisplay = (TextView) findViewById(R.id.tempDisplay);
        init();
    }


    private void init() {
        String task_name, task_date, task_time;
        ArrayList<Task> allTasks = TaskManager.getInstance().getAllTasks();

        for (Task ts : allTasks) {
            task_name = ts.getTaskName();
            task_date = ts.getTaskDate();
            task_time = ts.getTaskTime();

            tempDisplay.append(task_name + " " + task_date + " " + task_time + "\n");
        }
    }

    public void onClickAdd(View view) {

        Intent addTaskIntent = new Intent(getApplicationContext(), AddTask.class);
        startActivity(addTaskIntent);

    }
}
