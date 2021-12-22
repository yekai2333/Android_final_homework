package com.example.myapplication.lifecycleFirst;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class MyObserver1 implements LifecycleObserver {
    private static final String tag="LifeCycleObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onMyCreate(){
        Log.i(tag,"LifecycleObserver：CreateEvent");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMyStart(){
        Log.i(tag,"LifecycleObserver：StartEvent");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onMyResume(){
        Log.i(tag,"LifecycleObserver：ResumeEvent");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onMyPause(){
        Log.i(tag,"LifecycleObserver：PauseEvent");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMyStop(){
        Log.i(tag,"LifecycleObserver：StopEvent");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onMyDestroy(){
        Log.i(tag,"LifecycleObserver：DestroyEvent");
    }

}
