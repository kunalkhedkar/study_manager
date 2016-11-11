package com.example.kunal.studymanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowTask extends AppCompatActivity {
DatabaseHelper mydb;

    FloatingActionButton fab;
    TextView tempDisplay;
    ListView TaskList;
    SimpleAdapter simpleAdapter;

    private String[] FROM = new String[]{"TaskName", "TaskDate", "TaskTime"};
    private int[] TO = new int[]{R.id.task_name, R.id.task_date, R.id.task_time};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        setTitle("Task");
        mydb = new DatabaseHelper(this);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        fab = (FloatingActionButton) findViewById(R.id.fab_add);

        //       init();

        build_listview();


    }

    private void build_listview() {
        ArrayList<HashMap<String, String>> data = populateData();

        simpleAdapter = new SimpleAdapter(this, data, R.layout.task_detail_list_view, FROM, TO);
        TaskList = (ListView) findViewById(R.id.task_detail_list_view);
        TaskList.setAdapter(simpleAdapter);
    }

    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String task_name, task_date, task_time;
        Cursor result = mydb.getData_TASK();

        if(result.getCount()==0) {

        }
        else {
            while (result.moveToNext()) {
                task_name = result.getString(0);
                task_date = result.getString(1);
                task_time = result.getString(2);

                HashMap<String, String> tasksDetail = new HashMap<>();
                tasksDetail.put("TaskName", task_name);
                tasksDetail.put("TaskDate", task_date);
                tasksDetail.put("TaskTime", task_time);


                detail.add(tasksDetail);

            }
        }
        return detail;
    }


    public void onClickAdd(View view) {

        Intent addTaskIntent = new Intent(getApplicationContext(), AddTask.class);
        startActivity(addTaskIntent);

    }
    public void delete_task(View view){

        View parentRow = (View) view.getParent();
        final int position = TaskList.getPositionForView(parentRow);

        HashMap<String, Object> obj = (HashMap<String, Object>) TaskList.getAdapter().getItem(position);
        final String name = (String) obj.get("TaskName");
        final String date = (String) obj.get("TaskDate");
        final String time = (String) obj.get("TaskTime");



        AlertDialog.Builder abuild = new AlertDialog.Builder(ShowTask.this);
        abuild.setMessage("Do you want to delete "+name+"?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if(mydb.delete_task(name,date,time)) {
                            build_listview();
                            Toast.makeText(ShowTask.this, name+" deleted ", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(ShowTask.this, "Fail to delete record", Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = abuild.create();
        alert.show();






    }
}
