package com.example.myapplication.DB.EntityClass;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    //这个注解意为将ID作为主键，并且自动生成
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String name;            //用户名
    private String password;        //密码
    private int age;
    private String work;
    private String defaultCity;
    private int count;

    public User(String name, String password, int age, String work, String defaultCity, int count) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.work = work;
        this.defaultCity = defaultCity;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDefaultCity() {
        return defaultCity;
    }

    public void setDefaultCity(String defaultCity) {
        this.defaultCity = defaultCity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

