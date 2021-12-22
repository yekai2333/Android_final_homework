package com.example.myapplication.ui.History;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.DB.EntityClass.History;
import com.example.myapplication.DB.EntityClass.User;
import com.example.myapplication.DB.UserDatabase;
import com.example.myapplication.DB.dao.CityDao;
import com.example.myapplication.DB.dao.HistoryDao;
import com.example.myapplication.DB.dao.UserDao;
import com.example.myapplication.R;
import java.util.ArrayList;
import java.util.List;
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    private List<String> listData=new ArrayList<>();
    private static int UserId;
    private static View view;
    private ListView lv;
    private static Intent intent;
    private UserDatabase userDatabase;
    private UserDao userDao;
    private HistoryDao historyDao;
    private CityDao cityDao;
    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel=new ViewModelProvider(this).get(HistoryViewModel.class);
        view=inflater.inflate(R.layout.fragment_history, container, false);
        intent=getActivity().getIntent();
        UserId=Integer.parseInt(intent.getStringExtra("userId"));
//        UserId=1;
        userDatabase=UserDatabase.getInstance(view.getContext());
        userDao=userDatabase.getUserDao();
        historyDao=userDatabase.getHistoryDao();
        cityDao=userDatabase.getCityDao();
        lv=view.findViewById(R.id.lv_history);
        User nowUser=userDao.getUserByUserId(UserId);

        //插入item
        List<History> listHistory=historyDao.getAllHistoriesByUserId(UserId);
        int number = nowUser.getCount() > listHistory.size() ? listHistory.size() : nowUser.getCount();
        for(int i = 0; i < number; i ++){
            String s=listHistory.get(i).getCity()+
                    "   "+
                    listHistory.get(i).getDate()+
                    "   "+
                    listHistory.get(i).getType();
            listData.add(s);
        }
        lv.setAdapter(new ArrayAdapter<String>(
                view.getContext(), R.layout.support_simple_spinner_dropdown_item,listData));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}