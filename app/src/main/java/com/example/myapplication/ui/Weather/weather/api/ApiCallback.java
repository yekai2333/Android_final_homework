package com.example.myapplication.ui.Weather.weather.api;

public interface ApiCallback {
    void onSuccess(String res);

    void onFailure(Exception e);
}
