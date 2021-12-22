package com.example.myapplication.DB.EntityClass;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class History {
    //这个注解意为将ID作为主键，并且自动生成
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private int userId;
    private String city;
    private String date;
    private String fengxiang;
    private String fengli;
    private String type;
    private String high;
    private String low;

    public History(int userId, String city, String date, String fengxiang, String type, String fengli, String high, String low) {
        this.userId = userId;
        this.city = city;
        this.date = date;
        this.fengxiang = fengxiang;
        this.fengli = fengli;
        this.type = type;
        this.high = high;
        this.low = low;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}
