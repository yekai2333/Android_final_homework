package com.example.myapplication.utils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private MutableLiveData<String> msg;
    public MutableLiveData<String>getUserName(){
        if(msg == null)
            msg = new MutableLiveData<>();
        return msg;
    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }

}