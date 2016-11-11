package com.example.kunal.studymanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by kunal on 16/9/16.
 */
public class AlarmBroadcastReceiver extends BroadcastReceiver {
    private String TAG = "AlarmBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: Alarm ON -----------------[ done ]----------------------");
        String str=intent.getStringExtra("taskname");
        myNotification(context, str);
    }

    private void myNotification(Context context, String message) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("Remainder");
        mBuilder.setContentText(message);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_2);

        Notification notification = mBuilder.build();
        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        nm.notify(1, notification);


    }
}
