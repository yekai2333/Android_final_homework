package com.example.myapplication.login;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.DB.EntityClass.City;
import com.example.myapplication.DB.EntityClass.User;
import com.example.myapplication.DB.UserDatabase;
import com.example.myapplication.DB.dao.CityDao;
import com.example.myapplication.DB.dao.HistoryDao;
import com.example.myapplication.DB.dao.UserDao;
import com.example.myapplication.R;
//import com.example.myapplication.utils.MainViewModel;
import com.example.myapplication.utils.ToastUtil;

import java.util.List;
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class LoginActivity extends AppCompatActivity {
    private Bundle userinfo;
    private UserDatabase userDatabase;
    private UserDao userDao;
    private HistoryDao historyDao;
    private CityDao cityDao;
    private EditText editName;
    private EditText editPassword;
    private Button btnLogin;
    private CheckBox cbIsRemberPass;
    private String isMemory;

    private CheckBox ch;
    public void setUserinfo(Bundle userinfo) {
        this.userinfo = userinfo;
    }

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        userDatabase=UserDatabase.getInstance(this);
        userDao=userDatabase.getUserDao();
        historyDao=userDatabase.getHistoryDao();
        cityDao=userDatabase.getCityDao();
        editName=findViewById(R.id.login_name);
        editPassword=findViewById(R.id.login_password);
        btnLogin=findViewById(R.id.btn_login);

        SharedPreferences sp1 = getSharedPreferences("config", MODE_PRIVATE);
        String str_username = sp1.getString("username", "");
        //取出用户名
        String str_password = sp1.getString("password", "");//取出密码
        editName.setText(str_username);//自动填充用户名
        editPassword.setText(str_password);//自动填充密码

        if(str_username!=null && str_password!=null){
            dengLu();
        }

        Button addTest=findViewById(R.id.add_test);
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user =new User("admin","123456",114514,"杭州师范大学仓前","杭州",5);
                userDao.insertUser(user);
                User user1=userDao.getUserByNameAndPassword(user.getName(),user.getPassword());
                City city=new City(user1.getID(),user1.getDefaultCity());
                cityDao.insertCity(city);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dengLu();
            }
        });

    }

    private void dengLu(){
        String name = editName.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
            List<User> data = userDao.getAllUsers();
            boolean match = false;
            for (int i = 0; i < data.size(); i++) {
                User user = data.get(i);
                if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                    match = true;
                    break;
                } else {
                    match = false;
                }
            }
            if (match) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                User user1=userDao.getUserByNameAndPassword(name,password);
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("userId",String.valueOf(user1.getID()));
                startActivity(intent);
//                        finish();//销毁此Activity
            } else {
                Toast.makeText(LoginActivity.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
        }

        ch = (CheckBox) findViewById(R.id.cbIsRemberPass);
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if(ch.isChecked()){
            String mUsername = editName.getText().toString();
            String mPassword = editPassword.getText().toString();
            editor.putString("username",mUsername);
            editor.putString("password",mPassword);
            editor.commit();
        }else{
            editor.clear();
            editor.commit();
        }
    }

}