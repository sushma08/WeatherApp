package com.example.sushma.hw06;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Sushma on 10/16/2016.
 */
public class CitiesTable {
    static final String TABLENAME = "cities";
    static final String CITY = "cityName";
    static final String COUNTRY = "country";
    static final String TEMPERATURE = "temperature";
    static final String FAVORITE = "favorite";

    static public void onCreate(SQLiteDatabase db){
        Log.d("Hey in Notes","I am here too");
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+" (");
        sb.append(CITY + " text not null, ");
        sb.append(COUNTRY +" text not null, ");
        sb.append(TEMPERATURE +" text not null, ");
        sb.append(FAVORITE +" text not null);");
        try{
            Log.d("table creation",sb.toString());
            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
        CitiesTable.onCreate(db);
    }
}
