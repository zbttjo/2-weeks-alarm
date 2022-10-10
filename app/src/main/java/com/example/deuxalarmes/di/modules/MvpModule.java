package com.example.deuxalarmes.di.modules;

import com.example.deuxalarmes.activities.mvp.implementation.alarms.AlarmsModel;
import com.example.deuxalarmes.activities.mvp.implementation.alarms.AlarmsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MvpModule {

    private AlarmsPresenter mAlarmsPresenter;
    private AlarmsModel mAlarmsModel;

    @Provides
    public AlarmsPresenter provideAlarmsPresenter() {
        if (mAlarmsPresenter == null) {
            mAlarmsPresenter = new AlarmsPresenter();
        }

        return mAlarmsPresenter;
    }

    @Provides
    public AlarmsModel provideAlarmsModel() {
        if (mAlarmsModel == null) {
            mAlarmsModel = new AlarmsModel();
        }

        return mAlarmsModel;
    }

}
