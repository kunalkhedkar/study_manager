package com.example.kunal.studymanager;

import java.util.ArrayList;

/**
 * Created by kunal on 13/10/16.
 */
public class TaskManager {


    private static TaskManager singleton = null;


    private ArrayList<Task> tasks = new ArrayList<>();

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        if (singleton == null) {
            singleton = new TaskManager();
        }
        return singleton;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }
}
