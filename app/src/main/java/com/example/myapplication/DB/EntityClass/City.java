package com.example.myapplication.DB.EntityClass;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class City {
    //这个注解意为将ID作为主键，并且自动生成
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private int userId;
    private String city;

    public City(int userId, String city) {
        this.userId = userId;
        this.city = city;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
