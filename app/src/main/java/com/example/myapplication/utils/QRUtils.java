package com.example.myapplication.utils;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class QRUtils {
    private String content;
    private int width;
    private int height;
    private String charSet;
    private String errorLevel;
    private String margin;
    private int black;
    private int white;

    public QRUtils(String content, int width, int height, String charSet, String errorLevel, String margin, int black,
                   int white) {
        this.content = content;
        this.width = width;
        this.height = height;
        this.charSet = charSet;
        this.errorLevel = errorLevel;
        this.margin = margin;
        this.black = black;
        this.white = white;
    }

    public Bitmap create(){
        if (content.equals(""))
            return null;
        if (width<0 || height<0)
            return null;
        try{
            Hashtable<EncodeHintType,String> hints=new Hashtable<>();
            if (!charSet.equals(""))
                hints.put(EncodeHintType.CHARACTER_SET,charSet);
            if (!errorLevel.equals(""))
                hints.put(EncodeHintType.ERROR_CORRECTION,errorLevel);
            if (!margin.equals(""))
                hints.put(EncodeHintType.MARGIN,margin);
            BitMatrix matrix=new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);
            int[] pixels=new int[width*height];
            for (int y=0;y<height;y++){
                for (int x=0;x<width;x++){
                    if (matrix.get(x,y))
                        pixels[y*width+x]=black;
                    else
                        pixels[y*width+x]=white;
                }
            }
            Bitmap bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,width,0,0,width,height);
            return bitmap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}