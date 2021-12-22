package com.example.myapplication.HTTP;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.NonNull;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.myapplication.R;
@RequiresApi(api = Build.VERSION_CODES.N)
@TargetApi(Build.VERSION_CODES.N)
@SuppressLint("NewApi")
public class PictureHandler extends Handler {
    private View view;

    public PictureHandler(View view) {
        this.view = view;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        ImageView img=(ImageView) view.findViewById(R.id.QR);
        Log.d("TAG", "handleMessage: what "+msg.what);
        if (msg.what==1) {
            Bundle receive=msg.getData();
            byte[] picBytes = receive.getByteArray("pic");
            Bitmap pic = BitmapFactory.decodeByteArray(picBytes, 0, picBytes.length);
            img.setImageBitmap(pic);
        }
    }
}
