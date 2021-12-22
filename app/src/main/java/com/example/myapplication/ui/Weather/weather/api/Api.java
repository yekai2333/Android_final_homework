package com.example.myapplication.ui.Weather.weather.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {
    private static OkHttpClient client;
    public static Api api = new Api();
    public static Api config() {
        client = new OkHttpClient.Builder()
                .build();
        return api;
    }
    //ApiCallback  这是一个回调接  //  final ApiCallback callback它在这个地方用了那个回调接口
    public  void getRequest(String url, final ApiCallback callback) {
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        Request request=builder.get().url(url).build();
        //2 将Request封装为Call
        Call call = client.newCall(request);
        //3 执行Call
        //第五步发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
                callback.onFailure(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                这个是请求成功后返回的数据的方法
//                这个就是返回的数据 response
//                然后通过这句final String result = response.body().string();把他的返回数据体解析出来
                final String result = response.body().string();
//                通过这个我们刚才出创建的回调接口传出去  传到使用它的地方   比如现在我们要在mainactivity中使用一下他
                callback.onSuccess(result);
            }
        });
    }
}
