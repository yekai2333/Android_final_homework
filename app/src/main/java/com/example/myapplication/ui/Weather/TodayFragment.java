package com.example.myapplication.ui.Weather;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.DB.EntityClass.City;
import com.example.myapplication.DB.EntityClass.History;
import com.example.myapplication.DB.EntityClass.User;
import com.example.myapplication.DB.UserDatabase;
import com.example.myapplication.DB.dao.CityDao;
import com.example.myapplication.DB.dao.HistoryDao;
import com.example.myapplication.DB.dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.ui.Weather.weather.Weather;
import com.example.myapplication.ui.Weather.weather.WeatherString;
import com.example.myapplication.ui.Weather.weather.WeatherTool;
import com.example.myapplication.ui.Weather.weather.adapter.WeatherAdapter;
import com.example.myapplication.ui.Weather.weather.api.Api;
import com.example.myapplication.ui.Weather.weather.api.ApiCallback;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class TodayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TodayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment todayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView tvWeather;
    private String cityNum;
    private List<WeatherTool> weatherList = new ArrayList<>();
    String describe=null;
    TextView tvWeatherCity;
    TextView weatherTitle;
    ImageView imgWeatherCity;
    private WeatherString mWeather;
    RecyclerView recyclerView;

    private UserDatabase userDatabase;
    private UserDao userDao;
    private HistoryDao historyDao;
    private CityDao cityDao;
    private static User nowUser;
    private boolean flag = true;
    private static int UserId;
    private static Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        tvWeather = view.findViewById(R.id.tv_weather);
        cityNum = "杭州";
        recyclerView = view.findViewById(R.id.weatherList);

        intent=getActivity().getIntent();
        UserId=Integer.parseInt(intent.getStringExtra("userId"));
        userDatabase=UserDatabase.getInstance(view.getContext());
        historyDao= userDatabase.getHistoryDao();
        cityDao=userDatabase.getCityDao();
        userDao=userDatabase.getUserDao();
        nowUser=userDao.getUserByUserId(UserId);

        if(nowUser.getDefaultCity().toString()!=null){
            cityNum = nowUser.getDefaultCity().toString();
        }
        System.out.println("当前默认城市为："+nowUser.getDefaultCity().toString());
        init();

        tvWeatherCity = view.findViewById(R.id.weather_city);
        registerForContextMenu(tvWeatherCity);
        weatherTitle = view.findViewById(R.id.weather_title);
        imgWeatherCity = view.findViewById(R.id.img_weather_city);
        nowUser=userDao.getUserByUserId(UserId);
        weatherTitle.setText("当前城市："+cityNum);

        return view;
    }


    private int mCount= 0;
    private void init() {
        //        在这里使用刚才创建的那个工具类里的方法
        Api.config().getRequest("http://wthrcdn.etouch.cn/weather_mini?city="+cityNum, new ApiCallback() {
            @Override
            public void onSuccess(String res) {
                //         成功的时候在这里可以写你想要的操作
//          res 这个就是获取到的result
//          现在解析数据   用Gson解析  引入一下他的依赖
                Log.e("onSuccess", res);
                Gson gson = new Gson();
                final Weather weather = gson.fromJson(res, Weather.class);
//                tWeather = gson.fromJson(res, Weather.class);
//                返回的数据中有一个 "status": 1000, 判断数据是否请求成功
                if (weather.getStatus() == 1000) {
                    Log.i("获取成功了", "onSuccess: "+weather.getData().getCity());
//                  因为okhttp获取数据是异步的所以是在子线程中进行的  但是UI操作只能在主线程中进行所以需要用runOnUiThread开一个线程进入到主线程中显示数据
                    Looper.prepare();
                    Toast.makeText(getActivity(), "获取信息成功", Toast.LENGTH_SHORT).show();
                    Pattern pattern = Pattern.compile("[\\d]级");
                    Pattern pattern1 = Pattern.compile("[\\d]℃");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCount++;
                            //tvWeather.setText("获取一下城市吧:"+weather.getData().getCity());
                            tvWeather.setText("城市:"+weather.getData().getCity()+" \n"+
                                    "天气:"+weather.getData().getForecast().get(4).getType()+" \n"+
                                    "日期:"+weather.getData().getForecast().get(0).getDate()+" \n");
                            Matcher matcher = pattern.matcher(weather.getData().getForecast().get(0).getFengli());
                            String FengLiText = "";
                            if (matcher.find()) {
                                FengLiText = matcher.group(0);
                            }
                            String typeText = "天气："+weather.getData().getForecast().get(4).getType();
                            FengLiText = "风力："+ FengLiText;

                            if(flag){
                                WeatherTool City = new WeatherTool(weather.getData().getCity(), R.drawable.city);
                                weatherList.add(City);
                                WeatherTool Date = new WeatherTool(weather.getData().getForecast().get(0).getDate(), R.drawable.date);
                                weatherList.add(Date);
                                WeatherTool FengXiang = new WeatherTool(weather.getData().getForecast().get(0).getFengxiang(), R.drawable.fx);
                                weatherList.add(FengXiang);
                                WeatherTool Type = new WeatherTool(typeText, R.drawable.sun);
                                weatherList.add(Type);
                                WeatherTool FengLi = new WeatherTool(FengLiText, R.drawable.wind);
                                weatherList.add(FengLi);
                                WeatherTool High = new WeatherTool(weather.getData().getForecast().get(0).getHigh(), R.drawable.heigh);
                                weatherList.add(High);
                                WeatherTool Low = new WeatherTool(weather.getData().getForecast().get(0).getLow(), R.drawable.low);
                                weatherList.add(Low);
                                flag = false;
                            }

                            initView(weather.getData().getCity(),
                                    weather.getData().getForecast().get(0).getDate(),
                                    weather.getData().getForecast().get(0).getFengxiang(),
                                    typeText,
                                    FengLiText,
                                    weather.getData().getForecast().get(0).getHigh(),
                                    weather.getData().getForecast().get(0).getLow());

                            History history=new History(UserId, weatherList.get(0).getName(),
                                    weatherList.get(1).getName(),
                                    weatherList.get(2).getName(),
                                    weatherList.get(3).getName(),
                                    weatherList.get(4).getName(),
                                    weatherList.get(5).getName(),
                                    weatherList.get(6).getName());

                            List<History> listHistory=historyDao.getAllHistoriesByUserId(UserId);
                            if (listHistory.size()==0){
                                historyDao.insertHistory(history);
                            }
                            else{
                                for(int t=0;t<listHistory.size();t++){
                                    History history1=listHistory.get(t);
                                    if(history1.getDate().equals(history.getDate()) && history1.getCity().equals(history.getCity())){
                                        break;
                                    } else if(t==listHistory.size()-1){
                                        historyDao.insertHistory(history);
                                    }
                                }
                            }

                        }
                    });
                    Looper.loop();

                } else {
                    Looper.prepare();
                    Toast.makeText(getActivity(), "获取信息失败", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("onFailure", e.getMessage());
                Looper.prepare();
                Toast.makeText(getActivity(), "连接失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        WeatherAdapter adapter = new WeatherAdapter(weatherList);
        recyclerView.setAdapter(adapter);
    }

    public void initView(String mCity,String mDate,String mFx,String mType,String mFl,String mHigh,String mLow){
        WeatherTool City = new WeatherTool(mCity, R.drawable.city);
        WeatherTool Date = new WeatherTool(mDate, R.drawable.date);
        WeatherTool FengXiang = new WeatherTool(mFx, R.drawable.fx);
        WeatherTool Type = new WeatherTool(mType, R.drawable.sun);
        WeatherTool FengLi = new WeatherTool(mFl, R.drawable.wind);
        WeatherTool High = new WeatherTool(mHigh, R.drawable.heigh);
        WeatherTool Low = new WeatherTool(mLow, R.drawable.low);

        weatherList.add(0,City);
        weatherList.add(1,Date);
        weatherList.add(2,FengXiang);
        weatherList.add(3,Type);
        weatherList.add(4,FengLi);
        weatherList.add(5,High);
        weatherList.add(6,Low);
        System.out.println("mCount="+mCount);
        System.out.println(City.getName());
        System.out.println(Date.getName());
        System.out.println(FengXiang.getName());
        System.out.println(Type.getName());
        System.out.println(High.getName());
        System.out.println(Low.getName());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);

        //根据View来判断当前要产生哪个Menu
        if (v==tvWeatherCity) {
            List<City> cityList=cityDao.getCitiesByUserId(UserId);
            for (int i=0;i<cityList.size();i++){
                menu.add(0, i, i,cityList.get(i).getCity());
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        List<City> cityList=cityDao.getCitiesByUserId(UserId);
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();

        cityNum = cityList.get(item.getItemId()).getCity();

        imgWeatherCity.setBackground(getResources().getDrawable(R.drawable.weather));
        weatherTitle.setText("当前城市："+cityNum);
        init();

        return super.onContextItemSelected(item);
    }

    private void showCities(){

    }
}