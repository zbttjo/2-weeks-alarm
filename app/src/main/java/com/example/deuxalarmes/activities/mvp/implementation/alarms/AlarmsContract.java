package com.example.deuxalarmes.activities.mvp.implementation.alarms;

import com.example.deuxalarmes.activities.mvp._abstract.IMvpPresenter;
import com.example.deuxalarmes.activities.mvp._abstract.IMvpView;
import com.example.deuxalarmes.general.UniqueList;
import com.example.deuxalarmes.objects.Alarm;
import com.example.deuxalarmes.objects.Alarm;

interface AlarmsContract {

    interface View extends IMvpView {

        void showNoAlarmsLayout();
        void showAlarms(UniqueList<Alarm> alarms);
        void showAlarmsViews();

        void onAlarmAdded(Alarm alarm, int position);
        void onAlarmEdited(Alarm alarm, int position);
        void onAlarmDeleted(Alarm alarm, int position);

        boolean isNoAlarmsViewVisible();
    }
//
//    interface Presenter {
//        void addAlarm(Alarm alarm);
//        void editAlarm(Alarm alarm, boolean withCallback);
//        void deleteAlarm(Alarm alarm);
//    }
}
