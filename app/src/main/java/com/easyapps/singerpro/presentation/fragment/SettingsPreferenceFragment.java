package com.easyapps.singerpro.presentation.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.view.View;

import com.easyapps.singerpro.R;
import com.easyapps.singerpro.presentation.component.NumberPickerPreference;

/**
 * Created by daniel on 20/09/2016.
 * Fragment that holds the set of waiting and running timers accordingly the total timers chose by
 * user.
 */
public class SettingsPreferenceFragment extends PreferenceFragment {

    private static final String LYRIC_NAME = "LYRIC_NAME";
    private Context mContext;

    public static SettingsPreferenceFragment newInstance(String lyricName) {
        SettingsPreferenceFragment fragment = new SettingsPreferenceFragment();

        Bundle b = new Bundle();
        b.putString(LYRIC_NAME, lyricName);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            this.mContext = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        String fileName = b.getString(LYRIC_NAME);

        // load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        PreferenceScreen pScreen = getPreferenceManager().createPreferenceScreen(mContext);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        int totalTimers = getTotalTimers(fileName, preferences);
        int minValue = mContext.getResources().getInteger(R.integer.number_min_value_timer);
        int maxValue = mContext.getResources().getInteger(R.integer.number_max_value_timer);
        final int order = 99;

        // creating each timer configuration for each timer.
        for (int i = 0; i < totalTimers; i++) {
            setTimerStopped(pScreen, order + i, fileName, i, preferences, minValue, maxValue);
            setTimerRunning(pScreen, order + i + 1, fileName, i, preferences, minValue, maxValue);
            setPreferenceScreen(pScreen);
        }
        if (totalTimers > 0)
            addPreferencesFromResource(R.xml.preferences);
    }

    private int getTotalTimers(String fileName, SharedPreferences preferences) {
        int totalTimersDefault = getResources().getInteger(R.integer.number_min_value_count_timers);
        String totalTimersPrefKey = getResources().getString(R.string.pref_key_totalTimers);
        return preferences.getInt(totalTimersPrefKey + fileName, totalTimersDefault);
    }

    private void setTimerRunning(PreferenceScreen pScreen, int orderRunning,
                                 String fileName, int i, SharedPreferences preferences,
                                 int minValue, int maxValue) {
        NumberPickerPreference npTimeRunning =
                new NumberPickerPreference(mContext, minValue, maxValue);
        String keyRunning = getResources().getString(R.string.pref_key_timeRunning);
        String fullKey = keyRunning + fileName + i;

        npTimeRunning.setKey(fullKey);
        npTimeRunning.setTitle(getResources().getString(R.string.pref_title_timeRunning, i + 1));
        npTimeRunning.setSummary(R.string.pref_summary_timeRunning);
        npTimeRunning.setOrder(i + orderRunning);

        int timeRunningDefault = getResources().getInteger(R.integer.number_min_value_timer);
        int timeRunning = preferences.getInt(fullKey, timeRunningDefault);
        npTimeRunning.setValue(timeRunning);

        pScreen.addPreference(npTimeRunning);
    }

    private void setTimerStopped(PreferenceScreen pScreen, int orderWaiting,
                                 String fileName, int i, SharedPreferences preferences,
                                 int minValue, int maxValue) {
        NumberPickerPreference npTimeWaiting =
                new NumberPickerPreference(mContext, minValue, maxValue);
        String keyWaiting = getResources().getString(R.string.pref_key_timeWaiting);
        String fullKey = keyWaiting + fileName + i;

        npTimeWaiting.setKey(fullKey);
        npTimeWaiting.setTitle(getResources().getString(R.string.pref_title_timeWaiting, i + 1));
        npTimeWaiting.setSummary(R.string.pref_summary_timeWaiting);
        npTimeWaiting.setOrder(i + orderWaiting);

        int timeWaitingDefault = getResources().getInteger(R.integer.number_min_value_timer);
        int timeWaiting = preferences.getInt(fullKey, timeWaitingDefault);
        npTimeWaiting.setValue(timeWaiting);

        pScreen.addPreference(npTimeWaiting);
    }
}