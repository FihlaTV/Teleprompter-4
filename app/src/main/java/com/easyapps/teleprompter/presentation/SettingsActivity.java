package com.easyapps.teleprompter.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.easyapps.teleprompter.presentation.components.TimerPreferenceFragment;
import com.easyapps.teleprompter.presentation.helper.ActivityUtils;

/**
 * Created by daniel on 08/09/2016.
 * Settings activity for each lyric. The file name is received by parameter.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String fileName = ActivityUtils.getFileNameParameter(getIntent());
        if (fileName == null)
            throw new RuntimeException("File not found.");

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                TimerPreferenceFragment.newInstance(fileName)).commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        finish();
    }
}