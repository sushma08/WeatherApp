package com.example.sushma.hw06;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Sushma on 10/5/2016.
 */
public class Weather implements Serializable{

    private static Weather userInstance = null;
    String time, temperature, iconUrl, windSpeed, windDirection, climateType,
            humidity,pressure,date;

    public Weather(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", temperature='" + temperature + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", climateType='" + climateType + '\'' +
                ", humidity='" + humidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public static Weather createWeather(JSONObject weatherJSON) throws JSONException {

        Weather weather =new Weather();
        JSONObject main = weatherJSON.getJSONObject("main");
        weather.setTemperature(main.getString("temp"));
        weather.setPressure(main.getString("pressure"));
        weather.setHumidity(main.getString("humidity"));

        JSONArray weatherArray = weatherJSON.getJSONArray("weather");
        JSONObject weatherObj = weatherArray.getJSONObject(0);
        weather.setClimateType(weatherObj.getString("description"));
        weather.setIconUrl(weatherObj.getString("icon"));

        JSONObject wdir = weatherJSON.getJSONObject("wind");
        weather.setWindDirection(wdir.getString("deg")+(char) 0x00B0);
        weather.setWindSpeed(wdir.getString("speed"));

        String date_time = weatherJSON.getString("dt_txt");
        String[] str_array = date_time.split(" ");
        String date = str_array[0];
        String time = str_array[1];
        time = time.substring(1,2);

        String timeinAmPm = time;
        int timeAmPm = Integer.parseInt(timeinAmPm);
        if(timeAmPm>12){
            timeAmPm = timeAmPm - 12;
            timeinAmPm = timeAmPm+":00 pm";
        }else if(timeAmPm==0){
            timeinAmPm = "12:00 am";
        }else {
            timeinAmPm = timeAmPm+":00 am";
        }
        weather.setTime(timeinAmPm);
        weather.setDate(date);

        Log.d("Weather Obj",weather.toString());

        return weather;
    }
}
