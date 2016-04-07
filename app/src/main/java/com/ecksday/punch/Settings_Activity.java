package com.ecksday.punch;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Settings_Activity extends AppCompatActivity {
    public void resetScores(View v) {
        com.ecksday.punch.MainActivity.roundno = 1;
        com.ecksday.punch.MainActivity.p1score = 0;
        com.ecksday.punch.MainActivity.p2score = 0;
        com.ecksday.punch.MainActivity.p1streak = 0;
        com.ecksday.punch.MainActivity.p2streak = 0;
        Toast.makeText(this, "Scores Reset!",
                Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_settings);
        }
    }
}
