package com.example.deuxalarmes.logic.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.deuxalarmes.database.AlarmDatabaseHelper;
import com.example.deuxalarmes.database.tasks.LoadAlarmsTask;
import com.example.deuxalarmes.logic.WelloAlarmManager;

//import com.example.deuxalarmes.activities.mvp.implementation.alert.AlertActivity;
//import com.example.deuxalarmes.database.PreferencesManager;
//import com.example.deuxalarmes.database.tasks.SaveAlarmTask;
//import com.example.deuxalarmes.general.WelloApplication;
//import com.example.deuxalarmes.objects.Alarm;

public class BootAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//        if (TextUtils.equals(intent.getAction(), )) { //DOPISHI CHEREZ DEBUG
            AlarmDatabaseHelper databaseHelper = new AlarmDatabaseHelper(context);
            LoadAlarmsTask task = new LoadAlarmsTask(databaseHelper, alarms -> {
                WelloAlarmManager alarmManager = new WelloAlarmManager();
                alarmManager.scheduleNearestAlarm(alarms);
            });

            task.execute();
//        }
    }

}
