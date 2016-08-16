package com.example.hwhong.alarmapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private TimePicker timePicker;
    private TextView infoTV;
    private Button start, end;

    private Calendar calendar;
    private PendingIntent pendingIntent;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;

        initializeVars();

        final Intent intent = new Intent(this.context, AlarmReceiver.class);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                /*
                String hour;
                String min;

                if (timePicker.getHour() > 12) {
                    hour = String.valueOf(timePicker.getHour() - 12);
                } else {
                    hour = String.valueOf(timePicker.getHour());
                }

                if (timePicker.getMinute() < 10) {
                    min = "0" + String.valueOf(timePicker.getMinute());
                } else {
                    min = String.valueOf(timePicker.getMinute());
                }
                */

                final int hour = timePicker.getHour();
                final int minute = timePicker.getMinute();;

                String minute_string = String.valueOf(minute);
                String hour_string = String.valueOf(hour);

                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12) ;
                }


                intent.putExtra("val", "on");

                infoTV.setText(" Alarm ON!" + " \n " + "Set to: " + hour_string + ":" + minute_string);

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoTV.setText("Alarm OFF!");

                intent.putExtra("val", "off");

                sendBroadcast(intent);

                alarmManager.cancel(pendingIntent);
            }
        });



    }

    public void initializeVars() {
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        infoTV = (TextView) findViewById(R.id.infoTV);

        calendar = Calendar.getInstance();

        start = (Button) findViewById(R.id.setAlarm);
        end = (Button) findViewById(R.id.endAlarm);
    }
}
