package com.example.myapplication.DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.DB.EntityClass.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User... users);
    @Update
    void updateUser(User... users);
    @Delete
    void deleteUser(User... users);
    @Query("DELETE FROM USER")
    void deleteAllUsers();
    @Query("SELECT * FROM USER ORDER BY ID DESC")
    List<User> getAllUsers();
    @Query("SELECT * FROM USER WHERE name=:name and password=:password")
    User getUserByNameAndPassword(String name,String password);
    @Query("SELECT * FROM USER WHERE id=:userId")
    User getUserByUserId(int userId);
}