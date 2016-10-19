package com.example.sushma.hw06;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sushma on 10/16/2016.
 */
public class City {
    String cityName, country, temperature, favorite, date;

    public City(){

    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", temperature='" + temperature + '\'' +
                ", favorite='" + favorite + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public City(String cityName, String country, String temperature, String favorite, String date) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.favorite = favorite;
        this.date = date;

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

}
