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

public class ShowMark extends AppCompatActivity {
    DatabaseHelper mydb;

    FloatingActionButton fab;
    TextView tempDisplay;
    ListView marksList;
    SimpleAdapter simpleAdapter;

    private String[] FROM = new String[]{"ExamName", "SubjectName", "ScoreMakrs", "TotalMarks"};
    private int[] TO = new int[]{R.id.exam_name, R.id.subject_name, R.id.scored_marks, R.id.total_marks};

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent mainActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(mainActivityIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mark);
        setTitle("Marks");
        mydb=new DatabaseHelper(this);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        fab = (FloatingActionButton) findViewById(R.id.fab_add);


        build_listview();

        ArrayList<HashMap<String, String>> data = populateData();

        simpleAdapter = new SimpleAdapter(this, data, R.layout.marks_detail_list_item, FROM, TO);
        marksList = (ListView) findViewById(R.id.marks_detail_list_view);
        marksList.setAdapter(simpleAdapter);

    }

    private void build_listview() {

        ArrayList<HashMap<String, String>> data = populateData();

        simpleAdapter = new SimpleAdapter(this, data, R.layout.marks_detail_list_item, FROM, TO);
        marksList = (ListView) findViewById(R.id.marks_detail_list_view);
        marksList.setAdapter(simpleAdapter);
    }

    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String exam_name, subject_name;
        int score_marks, total_marks;

        Cursor result = mydb.getData_MARKS();

        if (result.getCount() == 0) {


        } else {
            while (result.moveToNext()) {
                exam_name = result.getString(0);
                subject_name = result.getString(1);
                score_marks = Integer.parseInt(result.getString(2));
                total_marks = Integer.parseInt(result.getString(3));


                HashMap<String, String> marksDetail = new HashMap<>();
                marksDetail.put("ExamName", exam_name);
                marksDetail.put("SubjectName", subject_name);
                marksDetail.put("ScoreMakrs", score_marks + "");
                marksDetail.put("TotalMarks", total_marks + "");

                detail.add(marksDetail);

            }
        }

        return detail;
    }


    public void onClickAdd(View view) {

        Intent addMarkIntent = new Intent(getApplicationContext(), AddMark.class);
        startActivity(addMarkIntent);

    }

    public void delete_marks(View view){

        View parentRow = (View) view.getParent();
        final int position = marksList.getPositionForView(parentRow);

        HashMap<String, Object> obj = (HashMap<String, Object>) marksList.getAdapter().getItem(position);
        final String Examname = (String) obj.get("ExamName");
        final String sub_name = (String) obj.get("SubjectName");
        final String score_marks = (String) obj.get("ScoreMakrs");
        final String total_marks = (String) obj.get("TotalMarks");





        AlertDialog.Builder abuild = new AlertDialog.Builder(ShowMark.this);
        abuild.setMessage("Do you want to delete "+Examname+"?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if(mydb.delete_marks(Examname,sub_name,Integer.parseInt(score_marks),Integer.parseInt(total_marks))){
                            build_listview();
                            Toast.makeText(ShowMark.this, "Record deleted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ShowMark.this, "Fail to delete record", Toast.LENGTH_SHORT).show();
                        }

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
