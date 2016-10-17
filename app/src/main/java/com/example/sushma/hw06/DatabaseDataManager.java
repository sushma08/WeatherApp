package com.example.sushma.hw06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Sushma on 10/15/2016.
 */
public class DatabaseDataManager {
    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private CityDAO cityDAO;

    public DatabaseDataManager(Context mContext){
        this.mContext = mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        cityDAO = new CityDAO(db);
    }

    public void close(){
        if(db!=null){
            db.close();
        }
    }

    public CityDAO getcityDAO(){
        return this.cityDAO;
    }

    public long saveNote(City note){
        return this.cityDAO.save(note);
    }

    public boolean updateNote(City note){
        return this.cityDAO.update(note);
    }

    public boolean deleteNote(City note){
        return this.cityDAO.delete(note);
    }

    public City getNote(long id){
        return this.cityDAO.get(id);
    }

    public List<City> getAllNotes(){
        return this.cityDAO.getAll();
    }
}
