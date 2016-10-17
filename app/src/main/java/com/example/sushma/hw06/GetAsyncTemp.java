package com.example.sushma.hw06;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sushma on 10/16/2016.
 */
public class GetAsyncTemp extends AsyncTask<String, Void, ArrayList<Weather>> {
    Temp activity;
    Context mContext;
    GetAsyncTemp(Temp activity, Context context){
        this.activity = activity;
        mContext = context;
    }
    @Override
    protected ArrayList<Weather> doInBackground(String... params) {
        publishProgress();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                String line = null;
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    try {
                        line = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return WeatherUtil.parseweather(sb.toString());
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        CityWeather.pd.setMessage("Loading Hourly Data...");
        CityWeather.pd.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Weather> weathers) {
        super.onPostExecute(weathers);
        CityWeather.pd.dismiss();
    }

    static public interface Temp
    {
        public void getTemp();
    }
}
