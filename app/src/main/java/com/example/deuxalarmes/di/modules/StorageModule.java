package com.example.deuxalarmes.di.modules;

import android.content.Context;

import com.example.deuxalarmes.database.AlarmDatabaseHelper;
import com.example.deuxalarmes.database.PreferencesManager;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    AlarmDatabaseHelper provideDatabase(Context context) {
        return new AlarmDatabaseHelper(context);
    }

    @Provides
    PreferencesManager providePreferences(Context context) {
        return new PreferencesManager(context);
    }

}