package com.example.myapplication.Activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    NavController navController;
    BottomNavigationView bnv;
    AppBarConfiguration appBarConfiguration;
    private Bundle userinfo;

    public void setUserinfo(Bundle userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("data");
        bnv = findViewById(R.id.bottomBar);
        initView();


    }


    private void initView() {
        navController = Navigation.findNavController(this, R.id.fl_content);
        appBarConfiguration = new AppBarConfiguration.Builder(bnv.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bnv, navController);

    }


}
