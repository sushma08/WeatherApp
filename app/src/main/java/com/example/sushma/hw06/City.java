package com.example.sushma.hw06;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sushma on 10/16/2016.
 */
public class City {
    String cityName, country, temperature, favorite;

    public City(){

    }

    public City(String cityName, String country, String temperature, String favorite) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.favorite = favorite;
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

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", temperature='" + temperature + '\'' +
                ", favorite='" + favorite + '\'' +
                '}';
    }
}
