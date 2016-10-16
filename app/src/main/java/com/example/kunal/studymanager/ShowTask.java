package com.example.kunal.studymanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowTask extends AppCompatActivity {

    FloatingActionButton fab;
    TextView tempDisplay;

    private String[] FROM = new String[]{"TaskName", "TaskDate", "TaskTime"};
    private int[] TO = new int[]{R.id.task_name, R.id.task_date, R.id.task_time};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        setTitle("Task");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        fab = (FloatingActionButton) findViewById(R.id.fab_add);

        //       init();


        ArrayList<HashMap<String, String>> data = populateData();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.task_detail_list_view, FROM, TO);
        ListView marksList = (ListView) findViewById(R.id.task_detail_list_view);
        marksList.setAdapter(simpleAdapter);
    }

    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String task_name, task_date, task_time;
        ArrayList<Task> allTAsks = TaskManager.getInstance().getAllTasks();

        for (Task tk : allTAsks) {
            task_name = tk.getTaskName();
            task_date = tk.getTaskDate();
            task_time = tk.getTaskTime();


            HashMap<String, String> tasksDetail = new HashMap<>();
            tasksDetail.put("TaskName", task_name);
            tasksDetail.put("TaskDate", task_date);
            tasksDetail.put("TaskTime", task_time);


            detail.add(tasksDetail);

        }

        return detail;
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
