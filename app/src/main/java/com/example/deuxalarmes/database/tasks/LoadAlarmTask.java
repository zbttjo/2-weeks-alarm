package com.example.deuxalarmes.database.tasks;

import com.example.deuxalarmes.database.AlarmDatabaseHelper;
import com.example.deuxalarmes.database.tasks._abstract.DatabaseTask;
import com.example.deuxalarmes.database.tasks._abstract.DatabaseTaskCallback;
import com.example.deuxalarmes.general.UniqueList;
import com.example.deuxalarmes.objects.Alarm;

public class LoadAlarmTask extends DatabaseTask<Alarm, Integer, Void, Alarm>{

    public LoadAlarmTask(AlarmDatabaseHelper database, UniqueList<Alarm> dataSource, DatabaseTaskCallback<Alarm> callback) {
        super(database, dataSource, callback);
    }

    @Override
    protected Alarm doInBackground(Integer... params) {
        return getDatabase().readAlarm(params[0]);
    }

}