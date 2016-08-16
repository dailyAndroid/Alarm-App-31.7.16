package com.example.hwhong.alarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hwhong on 7/31/16.
 */
public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String value = intent.getExtras().getString("val");

        //Intent intent1 = new Intent(context, RingtonePlay.class);
        //intent1.putExtra("val", value);
        //context.startService(intent1);

        Intent serviceIntent = new Intent(context,RingtonePlay.class);
        serviceIntent.putExtra("val", value);
        //serviceIntent.putExtra("quote id", richard_id);

        context.startService(serviceIntent);
    }
}
