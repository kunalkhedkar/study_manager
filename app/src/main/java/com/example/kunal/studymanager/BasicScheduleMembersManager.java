package com.example.kunal.studymanager;

import java.util.ArrayList;

/**
 * Created by kunal on 23/10/16.
 */
public class BasicScheduleMembersManager {

    private static BasicScheduleMembersManager singleton = null;


    private ArrayList<BasicScheduleMembers> scheduleMember = new ArrayList<>();

    private BasicScheduleMembersManager() {
    }

    public static BasicScheduleMembersManager getInstance() {
        if (singleton == null) {
            singleton = new BasicScheduleMembersManager();
        }
        return singleton;
    }

    public void addData(BasicScheduleMembers basicScheduleMembers) {
        scheduleMember.add(basicScheduleMembers);
    }

    public ArrayList<BasicScheduleMembers> getData() {
        return this.scheduleMember;
    }
}
