package com.example.kunal.studymanager;

/**
 * Created by kunal on 13/10/16.
 */
public class Task {

    private String TaskName, TaskDate, TaskTime;


    public Task(String task_name, String task_date, String task_time) {
        this.TaskName = task_name;
        this.TaskDate = task_date;
        this.TaskTime = task_time;
    }

    public String getTaskName() {
        return this.TaskName;
    }

    public String getTaskDate() {
        return this.TaskDate;
    }

    public String getTaskTime() {
        return this.TaskTime;
    }


}
