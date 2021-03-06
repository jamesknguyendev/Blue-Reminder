package com.myapp.jamesnguyen.bluereminder.controllers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.myapp.jamesnguyen.bluereminder.broadcast_receivers.AlarmBroadcastReceiver;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by jamesnguyen on 3/24/18.
 */

//this class is responsible for starting an alarm,
// showing notification to main,
public class AlarmController {

    public void startAlarm(Context context, int seconds){
        long currentTime = System.currentTimeMillis();
        Intent intent =new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context
                , 1
                , intent
                , PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime + seconds*1000, pendingIntent);
    }
}
