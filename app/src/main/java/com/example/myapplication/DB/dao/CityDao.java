package com.example.myapplication.DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.myapplication.DB.EntityClass.City;

import java.util.List;
@Dao
public interface CityDao {
    @Insert
    void insertCity(City... cities);
    @Update
    void updateCity(City... cities);
    @Delete
    void deleteCity(City... cities);
    @Query("DELETE FROM CITY")
    void deleteAllCities();
    @Query("SELECT * FROM CITY ORDER BY ID DESC")
    List<City> getAllCities();
    @Query("SELECT * FROM CITY where userId=:id")
    List<City> getCitiesByUserId(int id);
}
