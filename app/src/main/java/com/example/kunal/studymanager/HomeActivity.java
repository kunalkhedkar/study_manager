package com.example.kunal.studymanager;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper mydb;

    TextView dateshow;
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

        dateshow= (TextView) findViewById(R.id.Dateshow);
        set_TitleDate();





    }
    private void set_TitleDate() {
        dateshow.setBackgroundColor(Color.parseColor("#000000"));
        Calendar cal=Calendar.getInstance();

        String format = new SimpleDateFormat("EEEE, d MMMM yyyy").format(cal.getTime());
        format="Today - "+format;
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

        String nameOfEvent = null, startDates = null, endDates = null, descriptions = null;


        Uri event = Uri.parse("content://com.android.calendar/events");
        Cursor cursor = getContentResolver().query(
                event,
                new String[]{"calendar_id", "title", "eventLocation"}, null,
                null, null);
        cursor.moveToFirst();
        String CNames[] = new String[cursor.getCount()];

        String MYKEY = null;
        long MYID = 0;
        for (int i = 0; i < CNames.length; i++) {

            nameOfEvent = (cursor.getString(1));
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
