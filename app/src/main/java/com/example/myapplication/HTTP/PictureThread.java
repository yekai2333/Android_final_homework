package com.example.myapplication.HTTP;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class PictureThread extends Thread{
    private String url;
    private PictureHandler handler;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHandler(PictureHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try{
            OkHttpClient client=new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .build();
            Request request=new Request.Builder()
                    .url(url)
                    .build();
            Call call=client.newCall(request);
            /*Response response = call.execute();
            Log.d("TAG", "run: "+response.body().string());*/
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message=new Message();
                    Bundle send=new Bundle();
                    send.putString("status","访问失败");
                    message.setData(send);
                    message.what=0;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message message=new Message();
                    Bundle send=new Bundle();
                    String jsonStr=response.body().string();
                    try{
                        JSONObject data=new JSONObject(jsonStr);
                        JSONArray dataList= data.getJSONArray("dataList");
                        JSONObject item=dataList.getJSONObject(0);
                        String src=item.getString("src");
                        OkHttpClient client=new OkHttpClient.Builder()
                                .connectTimeout(10, TimeUnit.SECONDS)
                                .readTimeout(10,TimeUnit.SECONDS)
                                .writeTimeout(10,TimeUnit.SECONDS)
                                .build();
                        Request request=new Request.Builder()
                                .url(src)
                                .build();
                        Response picResponse=client.newCall(request).execute();
                        byte[] picBytes=picResponse.body().bytes();
                        send.putByteArray("pic",picBytes);
                        message.setData(send);
                        message.what=1;
                        handler.sendMessage(message);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}