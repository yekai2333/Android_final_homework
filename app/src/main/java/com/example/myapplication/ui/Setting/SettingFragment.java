package com.example.myapplication.ui.Setting;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.myapplication.DB.EntityClass.City;
import com.example.myapplication.DB.EntityClass.User;
import com.example.myapplication.DB.UserDatabase;
import com.example.myapplication.DB.dao.CityDao;
import com.example.myapplication.DB.dao.HistoryDao;
import com.example.myapplication.DB.dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.ui.Map.MapViesModel;
import com.example.myapplication.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class SettingFragment extends Fragment {
    private static View view;
    private static int UserId;
    private static User nowUser;
    private static Intent intent;
    private UserDatabase userDatabase;
    private UserDao userDao;
    private HistoryDao historyDao;
    private CityDao cityDao;
    private String mMapAdress;
    private SettingsViewModel mViewModel;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_setting, container, false);
        intent=getActivity().getIntent();
        UserId=Integer.parseInt(intent.getStringExtra("userId"));
        userDatabase=UserDatabase.getInstance(view.getContext());
        userDao=userDatabase.getUserDao();
        historyDao=userDatabase.getHistoryDao();
        cityDao=userDatabase.getCityDao();
        nowUser=userDao.getUserByUserId(UserId);

        //?????????????????????+??????
        changeDefaultCity();

        //?????????????????????+??????
        editUserInfo(nowUser);

        //???????????????spinner??????????????????
        Spinner spinnerNumber=view.findViewById(R.id.sp_number);
        setSpinnerDefaultValue(spinnerNumber,nowUser.getCount());

        addInterestCity();//????????????
        changeCountForHistory();//??????????????????
        selectAddress();//?????????????????????

        rememberPassword();

        view.getBackground().setAlpha(100);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }

    /**
     * spinner ??????????????????Spinner
     * value ????????????????????????
     */
    private void setSpinnerDefaultValue(Spinner spinner, int value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter();
        int size = apsAdapter.getCount();
        for (int i = 0; i < size; i++) {
            if (Integer.parseInt(apsAdapter.getItem(i).toString())==value) {
                spinner.setSelection(i,true);
                break;
            }
        }
    }

    private void changeDefaultCity(){
        EditText spinnerDefaultCity=view.findViewById(R.id.sp_default_city);
        Button btn=view.findViewById(R.id.btn_define_default_city);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String changedCity = spinnerDefaultCity.getText().toString();
                System.out.println("changedCity="+changedCity);
                List<City> cityList=cityDao.getCitiesByUserId(UserId);
                for(int i=0;i<cityList.size();i++){
                    if(cityList.get(i).getCity().equals(changedCity)){
                        ShowToast(view,"????????????????????????");
                        break;
                    } else if(i == cityList.size()-1){
                        City newCity=new City(UserId,changedCity);
                        cityDao.insertCity(newCity);
                        ShowToast(view,"????????????");
                    }
                }

                if(nowUser.getDefaultCity().equals(changedCity)){
                    ShowToast(view,"???????????????????????????");
                }else{
                    nowUser.setDefaultCity(changedCity);
                    userDao.updateUser(nowUser);
                    ShowToast(view,"????????????????????????");
                }
            }
        });
    }



    private void editUserInfo(User nowUser){
        User userInfo=nowUser;
        EditText editName=view.findViewById(R.id.et_name);
        EditText editPassword=view.findViewById(R.id.et_password);

        editName.setText(userInfo.getName().toString());
        editPassword.setText(userInfo.getPassword().toString());


        Button btnName=view.findViewById(R.id.btn_editName);
        Button btnEditPassword=view.findViewById(R.id.btn_editPassword);

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName.setFocusableInTouchMode(true);
                editName.setFocusable(true);
            }
        });
        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPassword.setFocusableInTouchMode(true);
                editPassword.setFocusable(true);
            }
        });


        Button btnNameDefine=view.findViewById(R.id.btn_name_define);
        Button btnPasswordDefine=view.findViewById(R.id.btn_password_define);

        btnNameDefine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInfo.setName(editName.getText().toString());
                userDao.updateUser(userInfo);
                editName.setFocusable(false);
                ShowToast(view,"?????????????????????");
            }
        });

        btnPasswordDefine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInfo.setPassword(editPassword.getText().toString());
                userDao.updateUser(userInfo);
                editPassword.setFocusable(false);
                ShowToast(view,"??????????????????");
            }
        });
    }


    private void ShowToast(View view,String s){
        ToastUtil.missToast(view.getContext());
        ToastUtil.showToast(view.getContext(),s,1);
    }

    //????????????
    private void addInterestCity(){

        EditText meditCity = view.findViewById(R.id.medit_city);
        Button btnAddCity=view.findViewById(R.id.btn_add_city);
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String interestCity = meditCity.getText().toString();

                List<City> cityList=cityDao.getCitiesByUserId(UserId);
                for(int i=0;i<cityList.size();i++){
                    if(cityList.get(i).getCity().equals(interestCity)){
                        ShowToast(view,"????????????????????????");
                        break;
                    } else if(i==cityList.size()-1){
                        City newCity=new City(UserId,interestCity);
                        cityDao.insertCity(newCity);
                        ShowToast(view,"????????????");
                    }
                }
            }
        });
    }

    /**
     * ???????????????????????????
     */
    private static int changedCount;
    private void changeCountForHistory(){
        Spinner spinnerCount=view.findViewById(R.id.sp_number);
        spinnerCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changedCount=Integer.parseInt(adapterView.getSelectedItem().toString());
                ShowToast(view,String.valueOf(changedCount));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button btnNumberDefine=view.findViewById(R.id.btn_number_define);
        btnNumberDefine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowUser.getCount()==changedCount){
                    ShowToast(view,"??????????????????");
                }else{
                    nowUser.setCount(changedCount);
                    userDao.updateUser(nowUser);
                    ShowToast(view,"??????????????????");
                }
            }
        });
    }

    private void rememberPassword(){
        Button rememberBtn = view.findViewById(R.id.btn_password_remember);
        Button unRememberBtn = view.findViewById(R.id.btn_password_cantremember);


        SharedPreferences sp = this.getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        rememberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUsername = nowUser.getName();
                String mPassword = nowUser.getPassword();
                editor.putString("username",mUsername);
                editor.putString("password",mPassword);
                editor.commit();
                ShowToast(view,"??????????????????");
            }
        });

        unRememberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast(view,"??????????????????");
                editor.clear();
                editor.commit();
            }
        });
    }

    private String[] province = new String[] {"??????","??????","??????","??????"};
    private String[][] city = new String[][]
            {
                    { "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "????????????", "????????????",
                            "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
                            "?????????" },
                    { "?????????", "?????????", "?????????", "?????????", "?????????" },
                    { "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
                            "?????????" },
                    { "??????", "??????", "??????"}
            };

    //???????????????
    private String[][][] county = new String[][][]
            {
                    {   //??????
                            {"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"},
                            {"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"}
                    },
                    {    //??????
                            {"???"},{"???"},{"???"},{"???"},{"???"}
                    },
                    {    //??????
                            {"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"},{"???"}
                    },
                    {    //??????
                            {"?????????","?????????","?????????","?????????"}, //??????
                            {"?????????","?????????","?????????","?????????","?????????","?????????"}, //??????
                            {"?????????","?????????","?????????"}  //??????
                    }
            };
    static int provincePosition = 3;
    ArrayAdapter<String> provinceAdapter = null;
    ArrayAdapter<String> cityAdapter = null;
    ArrayAdapter<String> countyAdapter = null;
    Spinner provinceSp = null;
    Spinner citySp = null;
    Spinner countySp = null;

    private void selectAddress(){
        provinceSp = view.findViewById(R.id.spin_province);
        citySp = view.findViewById(R.id.spin_city);
        countySp = view.findViewById(R.id.spin_county);

        provinceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,province);
        provinceSp.setAdapter(provinceAdapter);
        provinceSp.setSelection(3,true);

        cityAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,city[3]);
        citySp.setAdapter(cityAdapter);
        citySp.setSelection(0,true);

        countyAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,county[3][0]);
        citySp.setAdapter(countyAdapter);
        citySp.setSelection(0,true);

        provinceSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, city[position]);
                citySp.setAdapter(cityAdapter);
                provincePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }

        });


        citySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3)
            {
                countyAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, county[provincePosition][position]);
                countySp.setAdapter(countyAdapter);
                mMapAdress=arg0.getSelectedItem().toString();

                nowUser.setWork(mMapAdress);
                userDao.updateUser(nowUser);
//                MapViesModel viewModel = new ViewModelProvider(requireActivity(),new ViewModelProvider.NewInstanceFactory()).get(MapViesModel.class);
//                Button subBtn = view.findViewById(R.id.btn_address);
//                subBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        viewModel.getMag().setValue(mMapAdress);
//                        ToastUtil.showToast(getActivity(),"????????????", Toast.LENGTH_SHORT);
//                    }
//                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }

}