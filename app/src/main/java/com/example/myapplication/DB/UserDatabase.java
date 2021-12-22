package com.example.myapplication.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.DB.EntityClass.City;
import com.example.myapplication.DB.EntityClass.History;
import com.example.myapplication.DB.EntityClass.User;
import com.example.myapplication.DB.dao.CityDao;
import com.example.myapplication.DB.dao.HistoryDao;
import com.example.myapplication.DB.dao.UserDao;

@Database(entities = {User.class, History.class, City.class},version =1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
    public abstract HistoryDao getHistoryDao();
    public abstract CityDao getCityDao();
    private static volatile UserDatabase userDatabase;
    public static synchronized UserDatabase getInstance(Context c){
        if(userDatabase==null){
            userDatabase=createDB(c);
        }
        return userDatabase;
    }
    private static UserDatabase createDB(final Context c ){
        return Room.databaseBuilder(
                c,UserDatabase.class,"UserDb").allowMainThreadQueries().build();
    }
}