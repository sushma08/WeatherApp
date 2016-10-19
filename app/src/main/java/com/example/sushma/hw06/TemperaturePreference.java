package com.example.sushma.hw06;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TemperaturePreference extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPrefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreference(key);
    }

    private void updatePreference(String key){
        Preference preference = findPreference(key);
        if(preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            if(listPreference.getEntry().length()>0){
                listPreference.setSummary(listPreference.getEntry());
            }else{
                listPreference.setSummary("C");
            }
        }
    }
}
