package com.example.sushma.hw06;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    String cityNAme, countryName;
    public static DatabaseDataManager dm;
    TextView textView;
    RecyclerView recyclerView;
    public static CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        recyclerView = (RecyclerView) findViewById(R.id.rvContacts);

        dm = new DatabaseDataManager(this);
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String values = getData.getString("pref_title_key","C");

        if(dm.getAllNotes().size()!=0) {
            textView.setText("Saved Cities");
            adapter = new CityAdapter(this, dm.getAllNotes());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//            linearLayoutManager.setNotifyOnChange(true);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    public void showCityWeather(View view) {
        cityNAme = ((EditText) findViewById(R.id.editTextCity)).getText().toString();
        countryName = ((EditText) findViewById(R.id.editTextState)).getText().toString();
        if (cityNAme.equals("") || countryName.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter both city and state fields...", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, CityWeather.class);
            cityNAme = cityNAme.replace(' ', '_');
            intent.putExtra("city", cityNAme);
            intent.putExtra("state", countryName);
            startActivity(intent);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file,menu);
        menu.findItem(R.id.action).setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action1:
                Intent intent = new Intent();
                intent.setClassName(this, "com.example.sushma.hw06.TemperaturePreference");
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

/*    public void setupData(List<City> cities) {

    }*/

/*    @Override
    public void recycleView(List<City> cityList) {
            setupData(cityList);
    }*/
}
