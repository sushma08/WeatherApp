package com.example.sushma.hw06;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Sushma on 10/16/2016.
 */

/*
* Assignment #6
* Names: Vinayak Kolhapure and Sushma Reddy
* */

public class CitiesTable {
    static final String TABLENAME = "cities";
    static final String CITY = "cityName";
    static final String COUNTRY = "country";
    static final String TEMPERATURE = "temperature";
    static final String FAVORITE = "favorite";
    static final String DATE = "date";

    static public void onCreate(SQLiteDatabase db) {
        Log.d("Hey in Notes", "I am here too");
        StringBuilder sb = new StringBuilder();
<<<<<<< HEAD
        sb.append("CREATE TABLE " + TABLENAME + " (");
        sb.append(CITY + " text primary key not null, ");
        sb.append(COUNTRY + " text not null, ");
        sb.append(TEMPERATURE + " text not null, ");
        sb.append(DATE + " text not null, ");
        sb.append(FAVORITE + " text not null);");
        try {
            Log.d("table creation", sb.toString());
=======
        sb.append("CREATE TABLE "+TABLENAME+" (");
        sb.append(CITY + " text primary key not null, ");
        sb.append(COUNTRY +" text not null, ");
        sb.append(TEMPERATURE +" text not null, ");
        sb.append(DATE +" text not null, ");
        sb.append(FAVORITE +" text not null);");
        try{
            Log.d("table creation",sb.toString());
>>>>>>> 1002f1ea628e8d0a21c59bf27a8703c51a181c0a
            db.execSQL(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        CitiesTable.onCreate(db);
    }
}