package com.example.kunal;

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

import com.example.kunal.studymanager.AddSubject;
import com.example.kunal.studymanager.DatabaseHelper;
import com.example.kunal.studymanager.HomeActivity;
import com.example.kunal.studymanager.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowSubject extends AppCompatActivity {
    DatabaseHelper mydb;

    FloatingActionButton fab;
    TextView tempDisplay;
    ListView marksList;
    SimpleAdapter simpleAdapter;

    private String[] FROM = new String[]{"SubjectName", "Chpt"};
    private int[] TO = new int[]{R.id.SubjectName, R.id.chpt};

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent mainActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(mainActivityIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subject);
        setTitle("Subjects");
        mydb = new DatabaseHelper(this);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab_add);


        build_listview();


    }

    private void build_listview() {
        ArrayList<HashMap<String, String>> data = populateData();

        simpleAdapter = new SimpleAdapter(this, data, R.layout.subject_detail_list_view, FROM, TO);
        marksList = (ListView) findViewById(R.id.subject_detail_list_view);
        marksList.setAdapter(simpleAdapter);
    }

    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String subject_name;
        int chpt;

        //get data
        Cursor result = mydb.getData_SUBJECT();

        if(result.getCount()==0) {

        }
        else {
            while (result.moveToNext()) {
                subject_name = result.getString(0);
                chpt = Integer.parseInt(result.getString(1));

                HashMap<String, String> subjectDetail = new HashMap<>();
                subjectDetail.put("SubjectName", subject_name);
                subjectDetail.put("Chpt", chpt + "");
                detail.add(subjectDetail);
            }
        }

        return detail;
    }

    public void onClickAdd(View view) {

        Intent addSubjectIntent = new Intent(getApplicationContext(), AddSubject.class);
        startActivity(addSubjectIntent);

    }

    public void delete_subject(View view){

        View parentRow = (View) view.getParent();
        final int position = marksList.getPositionForView(parentRow);

        HashMap<String, Object> obj = (HashMap<String, Object>) marksList.getAdapter().getItem(position);
        final String name = (String) obj.get("SubjectName");

        AlertDialog.Builder abuild = new AlertDialog.Builder(ShowSubject.this);
        abuild.setMessage("Do you want to delete "+name+"?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if(mydb.delete_sub(name)) {
                            build_listview();
                            Toast.makeText(ShowSubject.this, name+"deleted ", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(ShowSubject.this, "Fail to delete record", Toast.LENGTH_SHORT).show();

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
