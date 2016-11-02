package com.example.sushma.hw06;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

<<<<<<< HEAD
/*
* Assignment #6
* Names: Vinayak Kolhapure and Sushma Reddy
* */


public class TemperaturePreference extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String KEY_LIST_VIEW_PREFERENCE = "key";
=======
public class TemperaturePreference extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a

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
<<<<<<< HEAD
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("KEY", listPreference.getEntry()+"");
            editor.commit();
            /*if(getIntent().getExtras()!=null){
                Intent intent = getIntent();
                String intentClass = intent.getStringExtra("class");
                String city = intent.getStringExtra("city");
                String country = intent.getStringExtra("state");
                if(intentClass.equals("CITY")){
                    Intent intent1 = new Intent(this, CityWeather.class);
                    intent1.putExtra("city", city);
                    intent1.putExtra("state", country);
                    startActivity(intent1);
                }
            }*/
=======
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
        }
    }
}
