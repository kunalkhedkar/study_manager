package com.example.kunal.studymanager;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ShowSchedule extends AppCompatActivity {

    int TotalSubject = 3, noOfSubPerDay = 2;
    TextView labelsub2, labelsub3;
    int position;

    private String[] FROM = new String[]{"Date", "Day", "Sub1", "Sub2", "Sub3"};
    private int[] TO = new int[]{R.id.date, R.id.day, R.id.sub1, R.id.sub2, R.id.sub3};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);
        setTitle("Your Schedule");

        ArrayList<BasicScheduleMembers> dataMembers = BasicScheduleMembersManager.getInstance().getData();

        for (BasicScheduleMembers bs : dataMembers) {
            TotalSubject = bs.gettotalSubject();
            noOfSubPerDay = bs.getNoOfSubPerDays();
        }

        ArrayList<HashMap<String, String>> data = populateData();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.show_scheduler_list_view, FROM, TO);
        ListView ScheduleList = (ListView) findViewById(R.id.Schedule_detail_list_view);

        ScheduleList.setAdapter(simpleAdapter);
        ScheduleList.setSelection(position);

    }





    private ArrayList<HashMap<String, String>> populateData() {

        ArrayList<HashMap<String, String>> detail = new ArrayList<>();

        String day=null, s1 = "none", s2 = "-", s3 = "-";
        String nameOfEvent = null, startDates = null, endDates = null, descriptions = null;

        Calendar cal=Calendar.getInstance();
        String today=DateFormat.getDateInstance().format(cal.getTimeInMillis());


        Uri event = Uri.parse("content://com.android.calendar/events");
        Cursor cursor = getContentResolver().query(
                event,
                new String[]{"calendar_id", "title", "description",
                        "dtstart", "dtend", "eventLocation"}, null,
                null, null);

        cursor.moveToFirst();
        String CNames[] = new String[cursor.getCount()];

        String MYKEY = null;
        long MYID = 0;
        for (int i = 0; i < CNames.length; i++) {

            nameOfEvent = (cursor.getString(1));
            //startDates = (getDate(Long.parseLong(cursor.getString(3))));
            startDates=DateFormat.getDateInstance().format((Long.parseLong(cursor.getString(3))));


            cal.setTimeInMillis(Long.parseLong(cursor.getString(3)));
            // find day
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            day = new DateFormatSymbols().getWeekdays()[dayOfWeek];


            //endDates=(getDate(Long.parseLong(cursor.getString(4))));
            descriptions = (cursor.getString(2));
            String locID = (cursor.getString(5));
            CNames[i] = cursor.getString(1);

            if (locID != null && !TextUtils.equals(locID, "")) {
                if (locID.contains("_")) {


                    String[] parts = locID.split("_");
                    if (parts.length == 2) {
                        MYKEY = parts[0];
                        if (TextUtils.equals(MYKEY, "PROJECTEVENTINDENTIFIRE"))
                            MYID = Long.parseLong(parts[1]);
                    }
                }
            }


            if (TextUtils.equals(MYKEY, "PROJECTEVENTINDENTIFIRE")) {

                if(noOfSubPerDay==1)
                {
                    s1=nameOfEvent;


                }
                else if(noOfSubPerDay==2){
                    String[] parts = nameOfEvent.split("_");
                    if (parts.length == 2) {
                        s1=parts[0];
                        s2=parts[1];
                    }
                }
                else if(noOfSubPerDay==3){
                    String[] parts = nameOfEvent.split("_");
                    if (parts.length == 3) {
                        s1=parts[0];
                        s2=parts[1];
                        s3=parts[2];
                    }
                }


                HashMap<String, String> tasksDetail = new HashMap<>();
                tasksDetail.put("Date", startDates);
                tasksDetail.put("Day",day);
                tasksDetail.put("Sub1", s1);
                tasksDetail.put("Sub2", s2);
                tasksDetail.put("Sub3",s3);
                detail.add(tasksDetail);


            }
            cursor.moveToNext();


        }

    return detail;
}
}
