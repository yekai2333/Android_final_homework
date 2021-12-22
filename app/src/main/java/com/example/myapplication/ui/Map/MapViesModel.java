package com.example.myapplication.ui.Map;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class MapViesModel extends ViewModel {
    private MutableLiveData<String> msg;
    public MutableLiveData<String>getMag(){
        if(msg == null)
            msg = new MutableLiveData<>();
        return msg;
    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }

}