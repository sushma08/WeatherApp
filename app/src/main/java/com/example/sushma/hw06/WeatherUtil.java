package com.example.sushma.hw06;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sushma on 10/5/2016.
 */

/*
* Assignment #6
* Names: Vinayak Kolhapure and Sushma Reddy
* */

public class WeatherUtil {
        public static ArrayList<Weather> parseweather(String result) throws JSONException, ParseException {
            ArrayList<Weather> weatherList = new ArrayList<Weather>();
            JSONObject root = new JSONObject(result);
            JSONArray weatherJSONArray = root.getJSONArray("list");
            for (int i = 0; i < weatherJSONArray.length(); i++) {
                JSONObject weatherJSON = weatherJSONArray.getJSONObject(i);
                Weather weather = Weather.createWeather(weatherJSON);
                weatherList.add(weather);
            }
            Log.d("Size of list is "," "+weatherList.size());
            return weatherList;
    }

    public static String convertDateToMMMddyyyy(String date, int format) {
        String convertedDate = null;

        DateFormat fromFormat = null;
        String stringForm = null;
        Date dateFinal = null;

        if(format == 0)
            fromFormat = new SimpleDateFormat("yyyy-MM-dd");
        else if(format == 1)
            fromFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss zzz");

        fromFormat.setLenient(false);
        DateFormat toFormat = new SimpleDateFormat("MMM dd, yyyy");
        toFormat.setLenient(false);
        try {
            dateFinal = fromFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        convertedDate = toFormat.format(dateFinal);

        return convertedDate;
    }

/*    public static void fromJSON(SharedPreferences prefs) {
        ArrayList<Weather> weathersLocal = new ArrayList<Weather>();
        Gson gson = new Gson();
        String json = prefs.getString("TAG", null);
        Type type = new TypeToken<ArrayList<Weather>>(){}.getType();
        weathersLocal = gson.fromJson(json, type);
        MainActivity.weatherObjs = weathersLocal;
    }

    public static void toJSON(SharedPreferences prefs, List<Weather> favorites) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(favorites);
        editor.putString("TAG", json);
        editor.commit();
    }*/
}
