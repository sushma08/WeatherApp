package com.example.sushma.hw06;

import android.content.Context;
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
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

/*
* Assignment #6
* Names: Vinayak Kolhapure and Sushma Reddy
* */

public class MainActivity extends AppCompatActivity {
=======

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a

    String cityNAme, countryName;
    public static DatabaseDataManager dm;
    TextView textView;
<<<<<<< HEAD
    public static RecyclerView recyclerView;
    public static CityAdapter adapter;
    public static List<City> citiesSaved;
    public Context context;

    @Override
    protected void onStart() {
        super.onStart();
        context = MainActivity.this;
        Log.d("demo","Create");
        if(dm.getAllNotes().size()> 0 || citiesSaved.size()>0) {
            textView = (TextView) findViewById(R.id.textView);
            textView.setText("Saved Cities");
        }
        display();
    }
=======
    RecyclerView recyclerView;
    public static CityAdapter adapter;
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
<<<<<<< HEAD
        recyclerView = (RecyclerView) findViewById(R.id.rvSavedList);
=======
        recyclerView = (RecyclerView) findViewById(R.id.rvContacts);
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a

        dm = new DatabaseDataManager(this);
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String values = getData.getString("pref_title_key","C");
<<<<<<< HEAD
        citiesSaved = new ArrayList<City>();
        citiesSaved = dm.getAllNotes();
        if(dm.getAllNotes().size()!=0) {
            textView.setText("Saved Cities");
            CityAdapter adapter = new CityAdapter(this,citiesSaved);

            recyclerView.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);

            /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                    recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                *//*@Override
                public void onLongItemClick(View view, int position) {
                    //Log.d("demo","Long click " + position);
                    City staticCityName = citiesSaved.get(position);
                    citiesSaved.remove(staticCityName);
                    dm.deleteNote(staticCityName);

                    CityAdapter adapter = new CityAdapter(MainActivity.this,citiesSaved);

                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                            LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }*//*
            }));*/

=======

        if(dm.getAllNotes().size()!=0) {
            textView.setText("Saved Cities");
            adapter = new CityAdapter(this, dm.getAllNotes());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//            linearLayoutManager.setNotifyOnChange(true);
            recyclerView.setLayoutManager(linearLayoutManager);
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
        }
    }

    public void showCityWeather(View view) {
        cityNAme = ((EditText) findViewById(R.id.editTextCity)).getText().toString();
        countryName = ((EditText) findViewById(R.id.editTextState)).getText().toString();
        if (cityNAme.equals("") || countryName.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter both staticCityName and state fields...", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, CityWeather.class);
            cityNAme = cityNAme.replace(' ', '_');
            intent.putExtra("staticCityName", cityNAme);
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
<<<<<<< HEAD
                intent.putExtra("class", "MAIN");
=======
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

<<<<<<< HEAD

    public static void updateFavorite(int position, String favorite) {
        City city = citiesSaved.get(position);
        city.setFavorite(favorite);
        boolean result = dm.updateNote(city);
        Log.d("demo", "Favorite Changed for "+ city.getCityName() + " to " + favorite + " and return " + result);
    }

    public void deleteLongPressedFavorite(int position) {
        City city = citiesSaved.get(position);
        boolean result = dm.deleteNote(city);
        citiesSaved.remove(city);
        if(result) {
            CityAdapter adapter = new CityAdapter(MainActivity.this,citiesSaved);

            recyclerView.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                    LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    public void openCityWeatherForSavedCity(int position, Context context) {
        City city = citiesSaved.get(position);
        String cityName = city.getCityName();
        String country = city.getCountry();
        if(context == null)
            Log.d("demo","Context is null");
        Intent intent = new Intent(context,CityWeather.class);
        cityName = cityName.replace(' ', '_');
        intent.putExtra("staticCityName", cityName);
        intent.putExtra("state", country);

        context.startActivity(intent);
    }

    public void display(){
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String values = getData.getString("KEY","C");
        Log.d("Values",values);
        if(dm.getAllNotes().size()!=0) {
            textView.setText("Saved Cities");
            adapter = new CityAdapter(this, dm.getAllNotes());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }
=======
/*    public void setupData(List<City> cities) {

    }*/

/*    @Override
    public void recycleView(List<City> cityList) {
            setupData(cityList);
    }*/
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
}
