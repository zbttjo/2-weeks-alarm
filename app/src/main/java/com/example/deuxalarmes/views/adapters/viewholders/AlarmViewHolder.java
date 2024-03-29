package com.example.deuxalarmes.views.adapters.viewholders;

import android.annotation.SuppressLint;
import android.support.annotation.StyleRes;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.deuxalarmes.R;
import com.example.deuxalarmes.objects.Alarm;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.deuxalarmes.general.Constants.WEEK_EVEN;
import static com.example.deuxalarmes.general.Constants.WEEK_ODD;
import static com.example.deuxalarmes.general.GeneralMethods.getTrueItemsCount;
import static com.example.deuxalarmes.general.GeneralMethods.getTimeText;
import static com.example.deuxalarmes.general.ResourceManager.getDayName;

public class AlarmViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.i_alarm_time)
    TextView mTime;

    @BindView(R.id.i_alarm_name)
    TextView mName;

    @BindView(R.id.i_alarm_days)
    GridLayout mDays;

    @BindView(R.id.i_alarm_weeks)
    GridLayout mWeeks;

    @BindView(R.id.i_alarm_switch)
    Switch mSwitch;

    private View mRootView;
    private PopupMenu mPopupMenu;

    public AlarmViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mRootView = itemView;
    }

    public void setData(Alarm data) {
        String name = data.getName();

        mTime.setText(getTimeText(data.getHours(), data.getMinutes()));
        mSwitch.setChecked(data.isEnabled());

        if (TextUtils.isEmpty(name)) {
            mName.setVisibility(View.GONE);
        } else {
            mName.setVisibility(View.VISIBLE);
            mName.setText(data.getName());
        }

        initGridLayouts(data);
    }

    public View getRootView() {
        return mRootView;
    }

    public Switch getSwitch() {
        return mSwitch;
    }

    public PopupMenu getPopupMenu() {
        if (mPopupMenu != null) return mPopupMenu;
        else return initPopup();
    }

    private PopupMenu initPopup() {
        ContextThemeWrapper wrapper = createContextThemeWrapper(R.style.MyPopupMenu);

        mPopupMenu = new PopupMenu(wrapper, mSwitch);
        mPopupMenu.inflate(R.menu.menu_alarm);

        return mPopupMenu;
    }

    private void initGridLayouts(Alarm alarm) {
        boolean[] days = alarm.getDays();
        int daysCount = getTrueItemsCount(days);
        fillDaysGridLayout(days, getTrueItemsCount(days));

        if (daysCount != 0) {
            fillWeeksGridLayout(alarm.getWeeks());
        } else mWeeks.setVisibility(View.GONE);
    }

    private void fillDaysGridLayout(boolean[] days, int trueItemsCount) {
        mDays.removeAllViews();
        ContextThemeWrapper contextThemeWrapper = createContextThemeWrapper(R.style.DayViewMini);

        switch (trueItemsCount) {
            case 0:
                addViewToGrid(mDays, getReadyDateView(contextThemeWrapper, R.string.once));
                break;
            case 7:
                addViewToGrid(mDays, getReadyDateView(contextThemeWrapper, R.string.everyday));
                break;
            default:
                for (int i = 0; i < days.length; i++) {
                    if (days[i]) {
                        String text = getDayName(mRootView.getContext().getResources(), i + 1, false);
                        addViewToGrid(mDays, getReadyDateView(contextThemeWrapper, text));
                    }
                }
        }
    }

    private void fillWeeksGridLayout(boolean[] weeks) {
        mWeeks.removeAllViews();
        ContextThemeWrapper contextThemeWrapper = createContextThemeWrapper(R.style.WeekViewMini);
        boolean weekly = weeks[WEEK_EVEN] == weeks[WEEK_ODD];

        if (weeks[WEEK_EVEN] || weekly) {
            addViewToGrid(mWeeks, getReadyDateView(contextThemeWrapper, R.string.ev));
        }

        if (weeks[WEEK_ODD] || weekly) {
            addViewToGrid(mWeeks, getReadyDateView(contextThemeWrapper, R.string.od));
        }
    }

    private TextView getReadyDateView(ContextThemeWrapper ctw, int textId) {
        return getReadyDateView(ctw, ctw.getString(textId));
    }

    private TextView getReadyDateView(ContextThemeWrapper ctw, String text) {
        TextView week = new TextView(ctw);
        week.setText(text);
        return week;
    }

    private static void addViewToGrid(GridLayout grid, View view) {
        GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.setMargins(0, 0, 6, 0);

        grid.addView(view, lp);
    }


    @SuppressLint("RestrictedApi")
    private ContextThemeWrapper createContextThemeWrapper(@StyleRes int styleId) {
        return new ContextThemeWrapper(mRootView.getContext(), styleId);
    }

}
