package com.example.sushma.hw06;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CityWeather extends AppCompatActivity implements GetAsyncTemp.Temp{

    public static String intentCityNam, intentCountryNam, city;
    public static ProgressDialog pd;

//    DisplayRecycle activity;
//    Context mContext;
//    CityWeather(DisplayRecycle activity, Context context){
//        this.activity = activity;
//        mContext = context;
//    }

    DisplayRecycle mListener;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);
        pd = new ProgressDialog(this);
        TextView tvLoc;
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String values = getData.getString("pref_title_key","C");
        Log.d("List value",values);
        mListener = (DisplayRecycle)context;
        //tvLoc = (TextView) findViewById(R.id.textViewLocation);

        if(getIntent().getExtras()!=null){
            Intent intent = getIntent();
            intentCityNam = intent.getStringExtra("city");
            intentCountryNam = intent.getStringExtra("state");
            city = intentCityNam.replace("_"," ");
            //tvLoc.setText(" "+city+", "+intentCountryNam);
            new GetAsyncTemp(this,this).execute("http://api.openweathermap.org/data/2.5/forecast?q="+intentCityNam+","+intentCountryNam+"&mode=json&units=metric&appid=69e734e8d7f3ecde5c495bbae50eff7f");
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action:
                City city = new City();
                city.setCountry(intentCountryNam);
                city.setCityName(intentCityNam);
                city.setFavorite("true");
                city.setTemperature("50");
                city.setDate("10/20/2016");
                List<City> notes = MainActivity.dm.getAllNotes();
                MainActivity.dm.saveNote(city);
                List<City> clist = MainActivity.dm.getAllNotes();
//                MainActivity.adapter.notifyDataSetChanged();
//                mListener.recycleView(clist);
                Log.d("City in database",notes.toString());
                Toast.makeText(getApplicationContext(), "Saved City", Toast.LENGTH_SHORT).show();

            case R.id.action1:
                Intent intent = new Intent();
                intent.setClassName(this, "com.example.sushma.hw06.TemperaturePreference");
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    static public interface DisplayRecycle{
        public void recycleView(List<City> cityList);
    }

    @Override
    public void getTemp() {

    }
}
