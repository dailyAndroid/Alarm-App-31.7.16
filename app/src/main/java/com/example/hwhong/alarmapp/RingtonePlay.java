package com.example.hwhong.alarmapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hwhong on 7/31/16.
 */
public class RingtonePlay extends Service {

    private MediaPlayer mediaPlayer;

    private boolean running;
    private int startId;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String value = intent.getExtras().getString("val");

        if (value != null) {
            switch (value) {
                case "on":
                    startId = 1;
                    break;
                case "off":
                    startId = 0;
                    break;
                default:
                    startId = 0;
                    break;
            }
        }

        if (!running && startId == 0) {
            //music no, wants start
            mediaPlayer = MediaPlayer.create(this, R.raw.ny);
            mediaPlayer.start();

            running = true;
            this.startId = 0;

        } else {
            mediaPlayer.stop();
            mediaPlayer.reset();

            running = false;
            this.startId = 0;
            //music runs, wants end
        }

        Log.d("playing?", "yes");

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        this.running = false;
        Toast.makeText(this, "Destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
