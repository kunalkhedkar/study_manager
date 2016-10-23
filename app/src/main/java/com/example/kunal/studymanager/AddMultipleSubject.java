package com.example.kunal.studymanager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;
import java.util.TimeZone;

public class AddMultipleSubject extends AppCompatActivity {
    private static final boolean TODO = false;
    private static final String TAG = "AddMultipleSubject";
    private int no_of_sub_day, repetition, totalSubjects, revisionDays;
    private long daysDiff;
    private Calendar LAST_DATE_OF_SCHEDULE = Calendar.getInstance();
    Calendar start = Calendar.getInstance();
    Calendar end1 = Calendar.getInstance();
    int Count_Arr_Days[];
    ArrayList<String> subjectArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_multiple_subject);
        setTitle("Add Subjects");
        LAST_DATE_OF_SCHEDULE.clear();

        getPreviousActivityData();

        designMyView();

    }


    private void designMyView() {

        for (int i = 1; i <= totalSubjects; i++) {
            TurnVisibilityON(i);
        }

    }


    private void TurnVisibilityON(int i) {

        String EDITsub = "subject" + Integer.toString(i);
        String EDITchpt = "chpt" + Integer.toString(i);

        int subid = getResources().getIdentifier(EDITsub, "id", getApplicationContext().getPackageName());
        EditText subed = (EditText) findViewById(subid);

        int chptid = getResources().getIdentifier(EDITchpt, "id", getApplicationContext().getPackageName());
        EditText chpted = (EditText) findViewById(chptid);

        subed.setVisibility(View.VISIBLE);
        chpted.setVisibility(View.VISIBLE);
    }


    private void getPreviousActivityData() {
        Bundle bundle = getIntent().getExtras();

        no_of_sub_day = bundle.getInt("no_of_sub_day");
        repetition = bundle.getInt("repetition");
        totalSubjects = bundle.getInt("totalSubjects");
        daysDiff = bundle.getLong("daysDiff");
        revisionDays = bundle.getInt("revisionDays");
        long date = bundle.getLong("lastDateSchedule");
        LAST_DATE_OF_SCHEDULE.setTimeInMillis(date);
        Count_Arr_Days = new int[totalSubjects];
        end1.setTimeInMillis(date);


    }


    /* ---------------------------------------------------------------
                                OK
    -------------------------------------------------------------------*/
    public void onClickOk(View view) {


        getDataInput_fromScreen();


        BuildSchedule();

        int i = 0;
        for (String sub : subjectArrayList) {
            Log.d(TAG, sub + "   :  " + Count_Arr_Days[i]);
            i++;

        }

        Intent scheduleNext = new Intent(getApplicationContext(), SchedulerNext.class);
        scheduleNext.putExtra("daysDiff", daysDiff);
        scheduleNext.putExtra("totalSubjects", totalSubjects);
        scheduleNext.putExtra("no_of_sub_day", no_of_sub_day);
        scheduleNext.putExtra("repetition", repetition);
        scheduleNext.putExtra("revisionDays", revisionDays);
        scheduleNext.putExtra("Count_array",Count_Arr_Days);

        startActivity(scheduleNext);
    }

    private void getDataInput_fromScreen() {

        for (int i = 1; i <= totalSubjects; i++) {

            String EDITsub = "subject" + Integer.toString(i);
            String EDITchpt = "chpt" + Integer.toString(i);

            int subid = getResources().getIdentifier(EDITsub, "id", getApplicationContext().getPackageName());
            EditText subed = (EditText) findViewById(subid);
            String sub_name = subed.getText().toString();

            int chptid = getResources().getIdentifier(EDITchpt, "id", getApplicationContext().getPackageName());
            EditText chpted = (EditText) findViewById(chptid);
            int chpt = Integer.parseInt(chpted.getText().toString());

            Subject sub = new Subject(sub_name, chpt);
            SubjectManager.getInstance().addSubject(sub);

        }
    }


    void IncrementCounter(String temp) {

        int i = 0;
        for (String sub : subjectArrayList) {
            if (sub.equals(temp)) {
                Count_Arr_Days[i]++;
            }
            i++;
        }


    }

    void CounterFunction(String title) {

        String[] parts = title.split("_");
        if (parts.length == 2) {
            IncrementCounter(parts[0]);
            IncrementCounter(parts[1]);
        }
        else if (parts.length == 3) {
            IncrementCounter(parts[0]);
            IncrementCounter(parts[1]);
            IncrementCounter(parts[2]);

        }
        else if(!title.equals("Revision") && parts.length==1){
            IncrementCounter(title);
        }



    }

    private boolean addEvent(String title, long date) {
        long eventID = 0;
        CounterFunction(title);
        ContentValues values = new ContentValues();
        ContentResolver resolver = getApplicationContext().getContentResolver();
        values.put("calendar_id", 1);
        values.put("title", title);
        values.put("allDay", 1);
        values.put("dtstart", date); // event starts at 11 minutes from now
        values.put("dtend", date + 60 * 60 * 1000); // ends 60 minutes from now
        //CALENDAR_DATE.getTimeInMillis() + 60 * 60 * 1000); // ends 60 minutes from now
        values.put("description", "Project specific task");
        values.put("hasAlarm", 1);
        values.put("eventLocation", "PROJECTEVENTINDENTIFIRE");


        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

        //ContentResolver resolver = getApplicationContext().getContentResolver();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return TODO;
        }
        Uri event = resolver.insert(CalendarContract.Events.CONTENT_URI, values);

        String eventIDString = event.getLastPathSegment();

        if (eventIDString != null)
            eventID = Long.parseLong(eventIDString);


        Log.d(TAG, "addEvent: " + eventID + " is Added\t" + date);

        // adding event id to


        String locID = "PROJECTEVENTINDENTIFIRE_" + Long.toString(eventID);

        ContentResolver cr = getContentResolver();
        //ContentValues values = new ContentValues();
        Uri updateUri = null;

        values.put(CalendarContract.Events.EVENT_LOCATION, locID);

        updateUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
        int rows = getContentResolver().update(updateUri, values, null, null);

        if (eventID != 0)
            return true;
        else {
            Toast.makeText(AddMultipleSubject.this, "Fail to add event", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    // sending subject to add event method

    public void BuildSchedule() {


        ArrayList<Subject> allSubjects = SubjectManager.getInstance().getAllSubjects();
        for (Subject sub : allSubjects) {
            subjectArrayList.add(sub.getName());
        }


        Calendar start = Calendar.getInstance();
        Calendar end = LAST_DATE_OF_SCHEDULE;
        // minus revison days
        end.add(Calendar.DATE, -(revisionDays-1));
        long TimeInMillis = 0;

        ListIterator<String> listIter = subjectArrayList.listIterator();
        String s1 = null, s2 = null, s3 = null, str = null;


        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {

            if (no_of_sub_day == 1) {

                if (listIter.hasNext()) {
                    str = listIter.next();
                } else {
                    listIter = subjectArrayList.listIterator();
                    str = listIter.next();
                }

            }

            if (no_of_sub_day == 2) {

                if (listIter.hasNext()) {
                    s1 = listIter.next();
                } else {
                    listIter = subjectArrayList.listIterator();
                    s1 = listIter.next();
                }

                if (listIter.hasNext()) {
                    s2 = listIter.next();
                } else {
                    listIter = subjectArrayList.listIterator();
                    s2 = listIter.next();
                }

                str = s1 + "_" + s2;

            }


            if (no_of_sub_day == 3) {

                if (listIter.hasNext()) {
                    s1 = listIter.next();
                } else {
                    listIter = subjectArrayList.listIterator();
                    s1 = listIter.next();
                }

                if (listIter.hasNext()) {
                    s2 = listIter.next();
                } else {
                    listIter = subjectArrayList.listIterator();
                    s2 = listIter.next();
                }

                if (listIter.hasNext()) {
                    s3 = listIter.next();
                } else {
                    listIter = subjectArrayList.listIterator();
                    s3 = listIter.next();
                }


                // repetitions


                str = s1 + "_" + s2 + "_" + s3;
            }

            TimeInMillis = date.getTime();
            if (repetition == 1) {
                addEvent(str, TimeInMillis);

            } else if (repetition == 2) {

                // repeat 1
                addEvent(str, TimeInMillis);


                //repeat 2
                start.add(Calendar.DATE, 1);
                date = start.getTime();
                TimeInMillis = date.getTime();
                addEvent(str, TimeInMillis);


            } else {

                // repeat 1
                addEvent(str, TimeInMillis);


                //repeat 2
                start.add(Calendar.DATE, 1);
                date = start.getTime();
                TimeInMillis = date.getTime();
                addEvent(str, TimeInMillis);


                //repeat 3
                start.add(Calendar.DATE, 1);
                date = start.getTime();
                TimeInMillis = date.getTime();
                addEvent(str, TimeInMillis);

            }


        }

        end1.add(Calendar.DATE,2);
        start=end;
        start.add(Calendar.DATE,1);


        for (Date date = start.getTime(); start.before(end1); start.add(Calendar.DATE, 1), date = start.getTime()) {
            TimeInMillis = date.getTime();
            addEvent("Revision",TimeInMillis);
        }

    }
}
