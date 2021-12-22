package com.example.myapplication.ui.Weather.weather;

public class WeatherTool {
    private String name;
    private int imageId;

    public WeatherTool(String name, int imageId){
        this.name = name;
        this.imageId = imageId;

    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
