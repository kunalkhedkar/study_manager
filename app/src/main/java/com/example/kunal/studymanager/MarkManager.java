package com.example.kunal.studymanager;

import java.util.ArrayList;

/**
 * Created by kunal on 9/10/16.
 */
public class MarkManager {

    private static MarkManager singleton = null;


    private ArrayList<Mark> marks = new ArrayList<>();

    private MarkManager() {
    }

    public static MarkManager getInstance() {
        if (singleton == null) {
            singleton = new MarkManager();
        }
        return singleton;
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public ArrayList<Mark> getAllMarks() {
        return this.marks;
    }


}
