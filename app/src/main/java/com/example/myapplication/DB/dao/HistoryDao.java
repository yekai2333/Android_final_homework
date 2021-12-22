package com.example.myapplication.DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.myapplication.DB.EntityClass.History;

import java.util.List;
@Dao
public interface HistoryDao {
    @Insert
    void insertHistory(History... histories);
    @Update
    void updateHistory(History... histories);
    @Delete
    void deleteHistory(History... histories);
    @Query("DELETE FROM HISTORY")
    void deleteAllGHistories();
    @Query("SELECT * FROM HISTORY ORDER BY ID DESC")
    List<History> getAllHistories();
    @Query("SELECT * FROM HISTORY where userId=:id ORDER BY ID DESC ")
    List<History> getAllHistoriesByUserId(int id);
}
