package com.example.deuxalarmes.di.components;

import android.content.Context;

import com.example.deuxalarmes.activities.mvp.implementation.alarms.AlarmsActivity;
import com.example.deuxalarmes.activities.mvp.implementation.alarms.AlarmsModel;
import com.example.deuxalarmes.activities.mvp.implementation.alarms.AlarmsPresenter;
import com.example.deuxalarmes.database.AlarmDatabaseHelper;
import com.example.deuxalarmes.database.PreferencesManager;
import com.example.deuxalarmes.di.modules.AppModule;
import com.example.deuxalarmes.di.modules.MvpModule;
import com.example.deuxalarmes.di.modules.StorageModule;
import com.example.deuxalarmes.logic.WelloAlarmManager;

import dagger.Component;

@Component(modules = {AppModule.class, StorageModule.class, MvpModule.class})
public interface AppComponent {

    void inject(AlarmsActivity object);
    void inject(AlarmsPresenter object);
    void inject(AlarmsModel object);

    void inject(WelloAlarmManager object);

    Context getContext();
    WelloAlarmManager getAlarmManager();

    AlarmDatabaseHelper getDatabase();
    PreferencesManager getPreferences();

    AlarmsPresenter getAlarmsPresenter();
    AlarmsModel getAlarmsModel();

}
