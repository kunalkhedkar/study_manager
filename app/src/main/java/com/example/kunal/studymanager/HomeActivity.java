package com.example.kunal.studymanager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunal.ShowSubject;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper mydb;


    int TotalSubject = 0, noOfSubPerDay = 0;

    TextView dateshow, today, tomorrow, task, exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mydb = new DatabaseHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // my stuff

        boolean ok = getScheduledata();
        dateshow = (TextView) findViewById(R.id.Dateshow);
        set_TitleDate();


        today = (TextView) findViewById(R.id.today);
        tomorrow = (TextView) findViewById(R.id.tomorrow);
        task = (TextView) findViewById(R.id.task);
        exam = (TextView) findViewById(R.id.exam);

        if (ok)
            build_home_view();


    }

    private void build_home_view() {

        set_today();
        set_tomorrow();
        try {
            set_task();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        set_exam();
    }

    private boolean getScheduledata() {
        Cursor result = mydb.getData_SCHEDULE();
        if (result.getCount() != 0) {
            while (result.moveToNext()) {
                TotalSubject = Integer.parseInt(result.getString(3));
                noOfSubPerDay = Integer.parseInt(result.getString(4));
                return true;
            }
        }
        return false;
    }


    private void set_today() {

        Calendar calendar = Calendar.getInstance();
        long endDay = calendar.getTimeInMillis();
        calendar.add(Calendar.DATE, -1);
        long startDay = calendar.getTimeInMillis();


        String[] projection = new String[]{BaseColumns._ID, CalendarContract.Events.TITLE, CalendarContract.Events.DESCRIPTION, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND, CalendarContract.Events.EVENT_LOCATION};
        String selection = CalendarContract.Events.DTSTART + " >= ? AND " + CalendarContract.Events.DTSTART + "<= ?";
        String[] selectionArgs = new String[]{Long.toString(startDay), Long.toString(endDay)};

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor cur = getContentResolver().query(CalendarContract.Events.CONTENT_URI, projection, selection, selectionArgs, null);

        if (cur.getCount() > 0) {
            cur.moveToFirst();


            String nameOfEvent = (cur.getString(1));
            String locID = (cur.getString(5));
            String MYKEY = null;
            if (locID != null && !TextUtils.equals(locID, "")) {
                if (locID.contains("_")) {


                    String[] parts = locID.split("_");
                    if (parts.length == 2) {
                        MYKEY = parts[0];

                    }
                }
            }

            if (TextUtils.equals(MYKEY, "PROJECTEVENTINDENTIFIRE")) {

                if (noOfSubPerDay == 1 || TextUtils.equals(nameOfEvent, "Revision")) {
                    today.setText(nameOfEvent);


                } else if (noOfSubPerDay == 2) {
                    String[] parts = nameOfEvent.split("_");
                    if (parts.length == 2) {
                        today.setText(parts[0]);
                        today.append("\n" + parts[1]);

                    }
                } else if (noOfSubPerDay == 3) {
                    String[] parts = nameOfEvent.split("_");
                    if (parts.length == 3) {
                        today.setText(parts[0]);
                        today.append("\n" + parts[1]);
                        today.append("\n" + parts[2]);

                    }
                }


            }
        }
    }


    private void set_tomorrow() {

        Calendar calendar = Calendar.getInstance();
        long startDay = calendar.getTimeInMillis();
        calendar.add(Calendar.DATE, 1);
        long endDay = calendar.getTimeInMillis();


        String[] projection = new String[]{BaseColumns._ID, CalendarContract.Events.TITLE, CalendarContract.Events.DESCRIPTION, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND, CalendarContract.Events.EVENT_LOCATION};
        String selection = CalendarContract.Events.DTSTART + " >= ? AND " + CalendarContract.Events.DTSTART + "<= ?";
        String[] selectionArgs = new String[]{Long.toString(startDay), Long.toString(endDay)};

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor cur = getContentResolver().query(CalendarContract.Events.CONTENT_URI, projection, selection, selectionArgs, null);

        if (cur.getCount() > 0) {
            cur.moveToFirst();


            String nameOfEvent = (cur.getString(1));
            String locID = (cur.getString(5));
            String MYKEY = null;
            if (locID != null && !TextUtils.equals(locID, "")) {
                if (locID.contains("_")) {


                    String[] parts = locID.split("_");
                    if (parts.length == 2) {
                        MYKEY = parts[0];

                    }
                }
            }

            if (TextUtils.equals(MYKEY, "PROJECTEVENTINDENTIFIRE")) {

                if (noOfSubPerDay == 1 || TextUtils.equals(nameOfEvent, "Revision")) {
                    tomorrow.setText(nameOfEvent);


                } else if (noOfSubPerDay == 2) {
                    String[] parts = nameOfEvent.split("_");
                    if (parts.length == 2) {
                        tomorrow.setText(parts[0]);
                        tomorrow.append("\n" + parts[1]);

                    }
                } else if (noOfSubPerDay == 3) {
                    String[] parts = nameOfEvent.split("_");
                    if (parts.length == 3) {
                        tomorrow.setText(parts[0]);
                        tomorrow.append("\n" + parts[1]);
                        tomorrow.append("\n" + parts[2]);

                    }
                }


            }
        }
    }

    void set_task() throws ParseException {
        String str_today, str_tomorrow;
        str_today = " tasks due today";
        str_tomorrow = " tasks due tomorrow";

        int int_today = 0, int_tomorrow = 0;

        Calendar cal = Calendar.getInstance();
        String task_date, current, next;
        Cursor result = mydb.getData_TASK();


        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        current = format1.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        next = format1.format(cal.getTime());

        if (result.getCount() == 0) {

        } else {
            while (result.moveToNext()) {
                task_date = result.getString(1);


                if (myCompare(current, task_date))
                    int_today++;
                if (myCompare(next, task_date))
                    int_tomorrow++;


            }
        }
        task.setText(int_today + "" + str_today);
        task.append("\n" + int_tomorrow + "" + str_tomorrow);

    }

    boolean myCompare(String s1, String s2) {

        if (s1.contains("/") && s2.contains("/")) {


            String[] p1 = s1.split("/");
            String[] p2 = s2.split("/");
            if (p1.length == 3 && p1.length == 3) {

                if (Integer.parseInt(p1[0]) == Integer.parseInt(p2[0]) &&
                        Integer.parseInt(p1[1]) == Integer.parseInt(p2[1]) &&
                        Integer.parseInt(p1[2]) == Integer.parseInt(p2[2])) {
                    return true;
                }

            }
        }
        return false;
    }


    void set_exam() {
        int exam_counter = 0;
        String exam_date;

        Calendar cmp, end;
        cmp = Calendar.getInstance();
        end = Calendar.getInstance();
        end.add(Calendar.DATE, 7);


        Cursor result = mydb.getData_EXAM();

        if (result.getCount() == 0) {

        } else {
            while (result.moveToNext()) {

                exam_date = result.getString(2);


                if (exam_date.contains("/")) {
                    String[] p1 = exam_date.split("/");
                    if (p1.length == 3) {
                        cmp.set(Integer.parseInt(p1[2]), Integer.parseInt(p1[1]), Integer.parseInt(p1[0]));
                    }

                }

                if (end.before(cmp))
                    exam_counter++;
            }


        }

        exam.setText(exam_counter + " exams in the next 7 days");
    }


    private void set_TitleDate() {
        dateshow.setBackgroundColor(Color.parseColor("#000000"));
        Calendar cal = Calendar.getInstance();

        String format = new SimpleDateFormat("EEEE, d MMMM yyyy").format(cal.getTime());
        format = "Today - " + format;
        dateshow.setText(format);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // delete all cal event

            Rest_Schedule();
            mydb.drop_schedule();
            Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Rest_Schedule() {




        Uri event = Uri.parse("content://com.android.calendar/events");
        Cursor cursor = getContentResolver().query(
                event,
                new String[]{"calendar_id", "title", "eventLocation"}, null,
                null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String CNames[] = new String[cursor.getCount()];

            String MYKEY = null;
            long MYID = 0;
            for (int i = 0; i < CNames.length; i++) {

                String locID = (cursor.getString(2));

                CNames[i] = cursor.getString(1);

                if (locID != null && !TextUtils.equals(locID, "")) {
                    if (locID.contains("_")) {
                        String[] parts = locID.split("_");
                        if (parts.length == 2) {
                            MYKEY = parts[0];
                            if (TextUtils.equals(MYKEY, "PROJECTEVENTINDENTIFIRE")) {
                                MYID = Long.parseLong(parts[1]);
                                del(MYID);
                            }
                        }
                    }
                }
                cursor.moveToNext();
            }


        }
    }

    public void del(long eventID) {

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        Uri deleteUri = null;
        deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
        getContentResolver().delete(deleteUri, null, null);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {                      // Schedule

            Cursor result = mydb.getData_SCHEDULE();
            if (result.getCount() == 0) {
                Intent scheduleData = new Intent(getApplicationContext(), ScheduleData.class);
                startActivity(scheduleData);
            } else {
                Intent showScheduleIntent = new Intent(getApplicationContext(), ShowSchedule.class);
                startActivity(showScheduleIntent);
            }


        } else if (id == R.id.nav_subject) {                // Subject


            Intent showSubjectIntent = new Intent(getApplicationContext(), ShowSubject.class);
            startActivity(showSubjectIntent);


        } else if (id == R.id.nav_task) {                      // task

            Intent showTaskIntent = new Intent(getApplicationContext(), ShowTask.class);
            startActivity(showTaskIntent);


        } else if (id == R.id.nav_exam) {                       // exam

            Intent showExamIntent = new Intent(getApplicationContext(), ShowExam.class);
            startActivity(showExamIntent);


        } else if (id == R.id.nav_mark) {                       // marks

            Intent showMarkIntent = new Intent(getApplicationContext(), ShowMark.class);
            startActivity(showMarkIntent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
}
