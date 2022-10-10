package com.example.deuxalarmes.logic.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.deuxalarmes.activities.mvp.implementation.alert.AlertActivity;
import com.example.deuxalarmes.database.AlarmDatabaseHelper;
import com.example.deuxalarmes.database.PreferencesManager;
import com.example.deuxalarmes.database.tasks.LoadAlarmsTask;
import com.example.deuxalarmes.database.tasks.SaveAlarmTask;
import com.example.deuxalarmes.logic.WelloAlarmManager;
import com.example.deuxalarmes.objects.Alarm;

import static com.example.deuxalarmes.objects.Alarm.KEY_ALARM;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    private boolean mNextAlarmWillBeScheduled;

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmDatabaseHelper databaseHelper = new AlarmDatabaseHelper(context);
        LoadAlarmsTask task = new LoadAlarmsTask(databaseHelper, alarms -> {
            PreferencesManager preferencesManager = new PreferencesManager(context);
            WelloAlarmManager alarmManager = new WelloAlarmManager();

            Alarm currentAlarm = alarms.getByKey(preferencesManager.getCurrentAlarmIdentifier());

            if (currentAlarm != null) {
                boolean alarmDisposable = currentAlarm.isDisposable();
                currentAlarm.setEnabled(!alarmDisposable);

                if (alarmDisposable) {
                    mNextAlarmWillBeScheduled = true;
                    SaveAlarmTask saveAlarmTask = new SaveAlarmTask(databaseHelper, alarms, true, position -> {
                        startAlertActivity(context, currentAlarm);
                        alarmManager.scheduleNextAlarm(alarms);
                    });

                    saveAlarmTask.execute(currentAlarm);
                }

            }

             if (!mNextAlarmWillBeScheduled) {
                 alarmManager.scheduleNextAlarm(alarms);
             }
        });

        task.execute();
    }

    private static void startAlertActivity(Context context, Alarm alarm) {
        Intent alarmActivity = new Intent(context, AlertActivity.class);

        alarmActivity.putExtra(KEY_ALARM, alarm);
        alarmActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(alarmActivity);
    }

}
