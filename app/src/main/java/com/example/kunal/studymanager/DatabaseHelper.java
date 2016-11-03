package com.example.kunal.studymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kunal on 27/10/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "study_scheduler_db";
    public static final String SUBJECT_TABLE = "subject_table";
    public static final String TASK_TABLE = "task_table";
    public static final String EXAM_TABLE = "exam_table";
    public static final String MARKS_TABLE = "marks_table";
    public static final String SCHEDULE_TABLE = "schedule_table";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + SUBJECT_TABLE + "(SUBJECT_NAME TEXT PRIMARY KEY,CHAPTERS INTEGER)");
        db.execSQL("create table " + TASK_TABLE + "(TASK_NAME TEXT,TASK_DATE TEXT,TASK_TIME TEXT)");
        db.execSQL("create table " + EXAM_TABLE + "(EXAM_NAME TEXT,SUBJECT_NAME,EXAM_DATE TEXT,EXAM_TIME TEXT)");
        db.execSQL("create table " + MARKS_TABLE + "(TEST_NAME TEXT,SUBJECT_NAME TEXT ,SCORE_MARKS INTEGER,TOTAL_MARKS INTEGER)");
        db.execSQL("create table " + SCHEDULE_TABLE + "(ST_DATE TEXT,END_DATE,TOTAL_DAYS INTEGER,TOTAL_SUBJECT INTEGER,NO_OF_CHPT_PER_DAY INTEGERE,REPETITION INTEGER,REVISION INTEGER)");
    }

    // -----------------------  SUBJECT -------------------------------------

    public boolean insertData_SUBJECT(String subName, int chpt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        long result = -1;

        contentValues.put("SUBJECT_NAME", subName);
        contentValues.put("CHAPTERS", chpt);

        try {
            result = db.insert(SUBJECT_TABLE, null, contentValues);
        }
        catch (Exception e){}

            if (result == -1)
                return false;
            else
                return true;

    }
    public Cursor getData_SUBJECT() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + SUBJECT_TABLE, null);
        return result;
    }


    // -----------------------  TASK -------------------------------------

    public boolean insertData_TASK(String task_name, String task_date, String task_time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("TASK_NAME", task_name);
        contentValues.put("TASK_DATE", task_date);
        contentValues.put("TASK_TIME", task_time);
        long result = db.insert(TASK_TABLE, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getData_TASK() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + TASK_TABLE, null);
        return result;
    }

    // -----------------------  EXAM -------------------------------------

    public boolean insertData_EXAM(String exam_name, String subject_name, String exam_date, String exam_time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("EXAM_NAME", exam_name);
        contentValues.put("SUBJECT_NAME", subject_name);
        contentValues.put("EXAM_TIME", exam_date);
        contentValues.put("EXAM_TIME", exam_time);
        long result = db.insert(EXAM_TABLE, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getData_EXAM() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + EXAM_TABLE, null);
        return result;
    }


    // -----------------------  MARKS -------------------------------------


    public boolean insertData_MARKS(String subject_name, String test_name, int score_mark, int total_mark) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("TEST_NAME", test_name);
        contentValues.put("SUBJECT_NAME", subject_name);
        contentValues.put("SCORE_MARKS", score_mark);
        contentValues.put("TOTAL_MARKS", total_mark);
        long result = db.insert(MARKS_TABLE, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getData_MARKS() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + MARKS_TABLE, null);
        return result;
    }


    // -----------------------  SCHEDULE -------------------------------------


    public boolean insertData_SCHEDULE(  String stdate,String eddate,long TotalDays,int totalSubject,int NoOfSubPerDays,int repetition,int revision) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("ST_DATE", stdate);                       // 0
        contentValues.put("END_DATE", eddate);                      // 1
        contentValues.put("TOTAL_DAYS", TotalDays);                 // 2
        contentValues.put("TOTAL_SUBJECT", totalSubject);           // 3
        contentValues.put("NO_OF_CHPT_PER_DAY", NoOfSubPerDays);    // 4
        contentValues.put("REPETITION", repetition);                // 5
        contentValues.put("REVISION", revision);                    // 6

        long result = db.insert(SCHEDULE_TABLE, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getData_SCHEDULE() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + SCHEDULE_TABLE, null);
        return result;
    }

    void drop_schedule(){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE);
        db.execSQL("delete from "+ SCHEDULE_TABLE);
        db.execSQL("delete from "+ SUBJECT_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + SUBJECT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + TASK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + EXAM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + MARKS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + SCHEDULE_TABLE);
        onCreate(db);

    }
}

