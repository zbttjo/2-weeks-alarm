package com.example.deuxalarmes.views.adapters;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.deuxalarmes.R;
import com.example.deuxalarmes.general.UniqueList;
import com.example.deuxalarmes.general.WelloApplication;
import com.example.deuxalarmes.objects.Alarm;
import com.example.deuxalarmes.views.adapters.viewholders.AlarmViewHolder;

public class AlarmsAdapter extends RecyclerView.Adapter<AlarmViewHolder> {

    private UniqueList<Alarm> mAlarms;
    private Callback mCallback;

    public AlarmsAdapter(Callback callback) {
        mAlarms = new UniqueList<>();
        this.mCallback = callback;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlarmViewHolder viewHolder, int position) {
        final Alarm currentAlarm = mAlarms.get(position);
        View rootView = viewHolder.getRootView();
        Switch switchView = viewHolder.getSwitch();

        rootView.setOnLongClickListener(view -> {
            PopupMenu menu = viewHolder.getPopupMenu();

            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        mCallback.editAlarm(currentAlarm);
                        break;
                    case R.id.action_delete:
                        mCallback.deleteAlarm(currentAlarm);
                        break;
                }
                return false;
            });

            menu.show();
            return false;
        });

        rootView.setOnClickListener(view -> mCallback.editAlarm(currentAlarm));
        switchView.setOnCheckedChangeListener((compoundButton, b) -> {
            if (currentAlarm.isEnabled() != b) {
                currentAlarm.setEnabled(b);
                mCallback.switchAlarm(currentAlarm);
            }
        });

        viewHolder.setData(currentAlarm);
    }

    @Override
    public int getItemCount() {
        return mAlarms.size();
    }

    public void setDataSource(UniqueList<Alarm> alarms) {
        this.mAlarms = alarms;
        notifyDataSetChanged();
    }

    public interface Callback {
        void deleteAlarm(Alarm alarm);
        void editAlarm(Alarm alarm);
        void switchAlarm(Alarm alarm);
    }
}
