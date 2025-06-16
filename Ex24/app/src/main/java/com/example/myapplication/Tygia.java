package com.example.myapplication;

import android.graphics.Bitmap;

public class Tygia {
    private String type;
    private String imageurl;
    private Bitmap bitmap;
    private String buycash;
    private String buyck;
    private String sellcash;
    private String sellck;

    public Tygia(){}
    public Tygia(Bitmap bitmap, String buycash, String buyck, String imageurl, String sellcash, String sellck, String type) {
        this.bitmap = bitmap;
        this.buycash = buycash;
        this.buyck = buyck;
        this.imageurl = imageurl;
        this.sellcash = sellcash;
        this.sellck = sellck;
        this.type = type;
    }

    public String getBuycash() {
        return buycash;
    }

    public void setBuycash(String buycash) {
        this.buycash = buycash;
    }

    public String getBuyck() {
        return buyck;
    }

    public void setBuyck(String buyck) {
        this.buyck = buyck;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSellcash() {
        return sellcash;
    }

    public void setSellcash(String sellcash) {
        this.sellcash = sellcash;
    }

    public String getSellck() {
        return sellck;
    }

    public void setSellck(String sellck) {
        this.sellck = sellck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
