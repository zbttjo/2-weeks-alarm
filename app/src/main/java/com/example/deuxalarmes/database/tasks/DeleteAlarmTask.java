package com.example.deuxalarmes.database.tasks;

import com.example.deuxalarmes.database.AlarmDatabaseHelper;
import com.example.deuxalarmes.database.tasks._abstract.DatabaseTask;
import com.example.deuxalarmes.database.tasks._abstract.DatabaseTaskCallback;
import com.example.deuxalarmes.general.UniqueList;
import com.example.deuxalarmes.objects.Alarm;

public class DeleteAlarmTask extends DatabaseTask<Alarm, Alarm, Void, Integer>{

    public DeleteAlarmTask(AlarmDatabaseHelper database, UniqueList<Alarm> dataSource, DatabaseTaskCallback<Integer> callback) {
        super(database, dataSource, callback);
    }

    @Override
    protected Integer doInBackground(Alarm... params) {
        Alarm alarm = params[0];
        UniqueList<Alarm> alarms = getSource();
        int position = alarms.getPositionByKey(alarm.getId());

        getDatabase().removeAlarm(alarm);
        alarms.remove(alarm);

        return position;
    }

}