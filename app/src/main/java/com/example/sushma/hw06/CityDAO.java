package com.example.sushma.hw06;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sushma on 10/16/2016.
 */

/*
* Assignment #6
* Names: Vinayak Kolhapure and Sushma Reddy
* */

public class CityDAO {
    private SQLiteDatabase db;

    public CityDAO(SQLiteDatabase db){
        this.db = db;
    }

    public long save(City City){
        ContentValues values = new ContentValues();
        values.put(CitiesTable.CITY, City.getCityName());
        values.put(CitiesTable.COUNTRY, City.getCountry());
        values.put(CitiesTable.TEMPERATURE, City.getTemperature());
        values.put(CitiesTable.DATE, City.getDate());
        values.put(CitiesTable.FAVORITE, City.getFavorite());
        return db.insert(CitiesTable.TABLENAME, null, values);
    }

    public boolean update(City City){
        ContentValues values = new ContentValues();
        values.put(CitiesTable.CITY, City.getCityName());
        values.put(CitiesTable.COUNTRY, City.getCountry());
        values.put(CitiesTable.TEMPERATURE, City.getTemperature());
        values.put(CitiesTable.DATE, City.getDate());
        values.put(CitiesTable.FAVORITE, City.getFavorite());
        return db.update(CitiesTable.TABLENAME, values, CitiesTable.CITY+"=?", new String[]{City.getCityName()+""})>0;
    }

    public boolean delete(City City){
        return db.delete(CitiesTable.TABLENAME, CitiesTable.CITY+"=?", new String[]{City.getCityName()+""})>0;
    }

    public City get(long id){
        City City = null;
        Cursor c = db.query(true, CitiesTable.TABLENAME, new String[]{CitiesTable.CITY, CitiesTable.COUNTRY, CitiesTable.TEMPERATURE,CitiesTable.DATE,CitiesTable.FAVORITE},
                CitiesTable.CITY+"=?", new String[]{id+""}, null, null, null,null,null);
        if(c!=null && c.moveToFirst()){
            City = buildCityFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return City;
    }

    public List<City> getAll(){
        List<City> Citys = new ArrayList<City>();
        Cursor c = db.query(CitiesTable.TABLENAME, new String[]{CitiesTable.CITY, CitiesTable.COUNTRY, CitiesTable.TEMPERATURE,CitiesTable.DATE,CitiesTable.FAVORITE},
                null, null, null, null, null);
        if(c!=null && c.moveToFirst()){
            do{
                City City = buildCityFromCursor(c);
                if(City!=null){
                    Citys.add(City);
                }
            }while(c.moveToNext());

            if(!c.isClosed()){
                c.close();
            }
        }
        return Citys;
    }

    private City buildCityFromCursor(Cursor c){
        City City = null;
        if(c!=null){
            City = new City();
            City.setCityName(c.getString(0));
            City.setCountry(c.getString(1));
            City.setTemperature(c.getString(2));
            City.setDate(c.getString(3));
            City.setFavorite(c.getString(4));
        }
        return City;
    }
}
