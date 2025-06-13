package com.example.intentservice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.security.Provider;
import java.util.List;
import java.util.Map;

public class Myservice extends android.app.Service {
    MediaPlayer mymedia;

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate() {
        super.onCreate();
        mymedia = MediaPlayer.create(Myservice.this, R.raw.trentinhbanduoitinhyeu);
        mymedia.setLooping(true);
    }

    public int onStartCommand(Intent intent, int flags, int startID) {
        if (mymedia.isPlaying()) mymedia.pause();
        else mymedia.start();
        return super.onStartCommand(intent, flags, startID);
    }

    public void onDestroy() {
        super.onDestroy();
        mymedia.stop();
    }
}
