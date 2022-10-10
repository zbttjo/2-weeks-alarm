package com.example.deuxalarmes.database.tasks;

import com.example.deuxalarmes.database.AlarmDatabaseHelper;
import com.example.deuxalarmes.database.tasks._abstract.DatabaseTask;
import com.example.deuxalarmes.database.tasks._abstract.DatabaseTaskCallback;
import com.example.deuxalarmes.general.UniqueList;
import com.example.deuxalarmes.objects.Alarm;

public class LoadAlarmsTask extends DatabaseTask<Alarm, Void, Void, UniqueList<Alarm>>{

    public LoadAlarmsTask(AlarmDatabaseHelper database, DatabaseTaskCallback<UniqueList<Alarm>> callback) {
        super(database, null, callback);
    }

    @Override
    protected UniqueList<Alarm> doInBackground(Void... voids) {
        return getDatabase().readAlarms();
    }

}