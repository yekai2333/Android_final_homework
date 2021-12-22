package com.example.myapplication.ui.History;

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
public class HistoryViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public HistoryViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }
    // TODO: Implement the ViewModel
    public LiveData<String> getText() {
        return mText;
    }
}