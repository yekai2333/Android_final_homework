package com.example.myapplication.ui.Weather;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class WeatherViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    // TODO: Implement the ViewModel
    public LiveData<String> getText() {
        return mText;
    }
}