package com.example.sushma.hw06;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
=======
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
=======
import java.util.List;

public class CityWeather extends AppCompatActivity implements GetAsyncTemp.Temp{
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a

/*
* Assignment #6
* Names: Vinayak Kolhapure and Sushma Reddy
* */

public class CityWeather extends AppCompatActivity implements GetAsyncTemp.ISetupData{

    public static String intentCityNam, intentCountryNam, staticCityName;
    public static ProgressDialog pd;
    public ArrayList<Weather> localWeathersList;
    public Map<Date,ArrayList<Weather>> weatherDayMap;
    public Map<Date,ArrayList<Weather>> sortedMap;
    RecyclerView recyclerView;
    RecyclerView recyclerViewDetail;
    TextView tvCityDetailheader = null;

    TextView header = null;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String values = getData.getString("KEY","C");
        if(values.equals("C")){
            new GetAsyncTemp(this,this).execute("http://api.openweathermap.org/data/2.5/forecast?q="+intentCityNam+","+intentCountryNam+"&mode=json&units=metric&appid=69e734e8d7f3ecde5c495bbae50eff7f");
        }else{
            new GetAsyncTemp(this,this).execute("http://api.openweathermap.org/data/2.5/forecast?q="+intentCityNam+","+intentCountryNam+"&mode=json&units=Imperial&appid=69e734e8d7f3ecde5c495bbae50eff7f");
        }
    }

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
        localWeathersList = new ArrayList<Weather>();
        weatherDayMap = new HashMap<Date,ArrayList<Weather>>();
        TextView tvLoc;
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String values = getData.getString("pref_title_key","C");
        Log.d("List value",values);
        mListener = (DisplayRecycle)context;
        //tvLoc = (TextView) findViewById(R.id.textViewLocation);
        header = (TextView) findViewById(R.id.tvCityHeader);
        tvCityDetailheader = (TextView) findViewById(R.id.tvCityDetailHeader);

        recyclerView = (RecyclerView) findViewById(R.id.weatherDay);
        recyclerViewDetail = (RecyclerView) findViewById(R.id.recyclerViewDetail);

        if(getIntent().getExtras()!=null){
            Intent intent = getIntent();
            intentCityNam = intent.getStringExtra("staticCityName");
            intentCountryNam = intent.getStringExtra("state");
            staticCityName = intentCityNam.replace("_"," ");
            staticCityName = staticCityName.substring(0,1).toUpperCase()+ staticCityName.substring(1).toLowerCase();
            header.setText("Daily Forecast for "+ staticCityName + ", "+ intentCountryNam.toUpperCase());

            //tvLoc.setText(" "+staticCityName+", "+intentCountryNam);
            SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String values = getData.getString("KEY","C");
            Log.d("Temp value",values);
            if(values.equals("C")){
                new GetAsyncTemp(this,this).execute("http://api.openweathermap.org/data/2.5/forecast?q="+intentCityNam+","+intentCountryNam+"&mode=json&units=metric&appid=69e734e8d7f3ecde5c495bbae50eff7f");
            }else{

                new GetAsyncTemp(this,this).execute("http://api.openweathermap.org/data/2.5/forecast?q="+intentCityNam+","+intentCountryNam+"&mode=json&units=Imperial&appid=69e734e8d7f3ecde5c495bbae50eff7f");
            }
        }

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                LinearLayout layout = (LinearLayout) rv.findChildViewUnder(e.getX(), e.getY());
                TextView tv = null;
                if(layout != null)
                    tv = (TextView) layout.getChildAt(0);
                Date dateFinal = null;
                if(tv != null) {
                    tvCityDetailheader.setText("Three Hourly Forcast on " + tv.getText().toString());
                    for (Map.Entry<Date, ArrayList<Weather>> entry : sortedMap.entrySet()) {
                        Date key = entry.getKey();
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(key);

                        String finalDate = WeatherUtil.convertDateToMMMddyyyy(date, 0);

                        if (finalDate.equals(tv.getText().toString())) {
                            ArrayList<Weather> list = entry.getValue();
                            CityWeatherDetailAdapter detailAdapter = new CityWeatherDetailAdapter(getApplicationContext(), list);
                            recyclerViewDetail.setAdapter(detailAdapter);
                            LinearLayoutManager linearLayoutManagerDetail = new LinearLayoutManager(getApplicationContext(),
                                    LinearLayoutManager.HORIZONTAL, false);
                            recyclerViewDetail.setLayoutManager(linearLayoutManagerDetail);
                        }
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.d("demo","2");
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                Log.d("demo","3");
            }
        });
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
<<<<<<< HEAD
                city.setCountry(intentCountryNam.toUpperCase());
                staticCityName = staticCityName.substring(0,1).toUpperCase()+staticCityName.substring(1).toLowerCase();
                city.setCityName(staticCityName);
                city.setFavorite("false");
                city.setTemperature(getCityAvgTemp());
                String date = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss zzz").format(new Date());
                String newDate = WeatherUtil.convertDateToMMMddyyyy(date,1);
                city.setDate(newDate);
                List<City> notes = MainActivity.dm.getAllNotes();
                MainActivity.dm.saveNote(city);
                List<City> clist = MainActivity.dm.getAllNotes();
                MainActivity.citiesSaved = clist;
                CityAdapter adapter = new CityAdapter(this,clist);

                MainActivity.recyclerView.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                MainActivity.recyclerView.setLayoutManager(linearLayoutManager);
=======
                city.setCountry(intentCountryNam);
                city.setCityName(intentCityNam);
                city.setFavorite("true");
                city.setTemperature("50");
                city.setDate("10/20/2016");
                List<City> notes = MainActivity.dm.getAllNotes();
                MainActivity.dm.saveNote(city);
                List<City> clist = MainActivity.dm.getAllNotes();
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
//                MainActivity.adapter.notifyDataSetChanged();
//                mListener.recycleView(clist);
                Log.d("City in database",notes.toString());
                Toast.makeText(getApplicationContext(), "Saved City", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
                return true;
            case R.id.action1:
                //Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClassName(this, "com.example.sushma.hw06.TemperaturePreference");
                intent.putExtra("class", "CITY");
                intent.putExtra("city", intentCityNam);
                intent.putExtra("state", intentCountryNam);
=======

            case R.id.action1:
                Intent intent = new Intent();
                intent.setClassName(this, "com.example.sushma.hw06.TemperaturePreference");
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    static public interface DisplayRecycle{
        public void recycleView(List<City> cityList);
    }

    @Override
    public void setupWeathers(ArrayList<Weather> weathers) {
        localWeathersList = weathers;
        Collections.sort(localWeathersList,Weather.LatestOrder);
        int count = 0;
        ArrayList<Weather> weatherListForMap=null;
        for(Weather temp : localWeathersList) {
            if(count == 0) {
               weatherListForMap = new ArrayList<Weather>();
            }
            weatherListForMap.add(temp);
            if(count<=8) {
                count +=1;
                if(count == 8) {
                    count = 0;
                    weatherDayMap.put(temp.getDate(),weatherListForMap);
                }
            }
        }
        sortedMap = new TreeMap<Date,ArrayList<Weather>>(weatherDayMap);
        //Log.d("demo","check");
        setUpDayView();
    }

    private void setUpDayView() {
        ArrayList<Weather> weatherForDays = new ArrayList<Weather>();
        for (Map.Entry<Date, ArrayList<Weather>> entry : sortedMap.entrySet()) {
            Weather weather = new Weather();
            Date key = entry.getKey();
            Log.d("demo",key.toString());
            ArrayList<Weather> list = entry.getValue();
            Double temperatures = 0.0;

            weather.setDate(key);
            weather.setIconUrl(list.get(4).getIconUrl());
            for (Weather item : list) {
                temperatures += Double.valueOf(item.getTemperature());
            }
            Double average = temperatures/list.size();
            weather.setTemperature(String.format("%.2f", average));
            weatherForDays.add(weather);
        }

        //Iterate over Map and setup view for each day.

        CityWeatherAdapter adapter = new CityWeatherAdapter(this, weatherForDays);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*CityWeatherDetailAdapter detailAdapter = new CityWeatherDetailAdapter(this, localWeathersList);
        recyclerViewDetail.setAdapter(detailAdapter);
        LinearLayoutManager linearLayoutManagerDetail = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewDetail.setLayoutManager(linearLayoutManagerDetail);*/
    }

    private String getCityAvgTemp () {
        double avg = 0.0;
        if (localWeathersList.size() > 0) {
            for (Weather weather : localWeathersList) {
                avg += Double.valueOf(weather.getTemperature());
            }
            avg = avg / localWeathersList.size();
        }
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String values = getData.getString("KEY", "C");
        Log.d("Temp value before", avg + "");
        if (values.equals("C")) {
            return String.format("%.2f", avg);
        } else {
            avg = (avg - 32) * 0.56;
            Log.d("Temp value", avg + "");
            return String.format("%.2f", avg);
        }
    }
}
