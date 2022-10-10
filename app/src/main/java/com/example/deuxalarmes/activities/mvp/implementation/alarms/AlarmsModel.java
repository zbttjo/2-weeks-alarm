package com.example.deuxalarmes.activities.mvp.implementation.alarms;

import com.example.deuxalarmes.activities.mvp._abstract.IMvpModel;
import com.example.deuxalarmes.database.AlarmDatabaseHelper;
import com.example.deuxalarmes.database.tasks.DeleteAlarmTask;
import com.example.deuxalarmes.database.tasks.LoadAlarmsTask;
import com.example.deuxalarmes.database.tasks.SaveAlarmTask;
import com.example.deuxalarmes.database.tasks._abstract.DatabaseTask;
import com.example.deuxalarmes.database.tasks._abstract.DatabaseTaskCallback;
import com.example.deuxalarmes.general.UniqueList;
import com.example.deuxalarmes.general.WelloApplication;
import com.example.deuxalarmes.objects.Alarm;

import javax.inject.Inject;

public class AlarmsModel implements IMvpModel {

    @Inject
    AlarmDatabaseHelper mDatabase;

    private UniqueList<Alarm> mAlarms;

    private DatabaseTask mActiveReadingTask;

    public AlarmsModel() {
        WelloApplication.getComponent().inject(this);
    }

    void loadAlarms(boolean preventLoadFromDatabase, DatabaseTaskCallback<UniqueList<Alarm>> callback) {
        if (mAlarms != null && !preventLoadFromDatabase) {
            callback.onTaskDone(mAlarms);
        } else {
            LoadAlarmsTask task = new LoadAlarmsTask(mDatabase, result -> {
                mActiveReadingTask = null;
                mAlarms = result;
                callback.onTaskDone(result);
            });

            this.mActiveReadingTask = task;
            task.execute();
        }
    }

    void saveAlarm(Alarm alarm, boolean update, DatabaseTaskCallback<Integer> callback) {
        SaveAlarmTask task = new SaveAlarmTask(mDatabase, mAlarms, update, callback);
        task.execute(alarm);
    }

    void deleteAlarm(Alarm alarm, DatabaseTaskCallback<Integer> callback) {
        DeleteAlarmTask task = new DeleteAlarmTask(mDatabase, mAlarms, callback);
        task.execute(alarm);
    }

    @Override
    public void destroy() {
        if (mActiveReadingTask != null) {
            mActiveReadingTask.cancel(true);
        }
    }
}