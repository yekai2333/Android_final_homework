package com.example.myapplication.ui.Weather.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.Weather.weather.WeatherTool;
import com.example.myapplication.R;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    private List<WeatherTool> mWeatherList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView weatherImage;
        TextView weatherName;

        public ViewHolder (View view)
        {
            super(view);
            weatherImage = (ImageView) view.findViewById(R.id.icon_weather);
            weatherName = (TextView) view.findViewById(R.id.weather_name);
        }

    }

    public  WeatherAdapter (List <WeatherTool> weatherList){
        mWeatherList = weatherList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        WeatherTool weatherTool = mWeatherList.get(position);
        holder.weatherImage.setImageResource(weatherTool.getImageId());
        holder.weatherName.setText(weatherTool.getName());
    }

    @Override
    public int getItemCount(){
        return mWeatherList.size();
    }
}
