package com.example.btvn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private NumberPicker numberPicker;
    private Button btnSetAlarm, btnStopAlarm;
    private Vibrator vibrator;
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timePicker = findViewById(R.id.timePicker);
        numberPicker = findViewById(R.id.numberPicker);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        btnStopAlarm = findViewById(R.id.btnStopAlarm);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(60);

        vibrator =(Vibrator)getSystemService(VIBRATOR_SERVICE);

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        btnStopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAlarm();
            }
        });
    }

    private void setAlarm() {
        Calendar calendar =Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());

        calendar.set(Calendar.MINUTE, timePicker.getMinute());
        calendar.add(Calendar.MINUTE,numberPicker.getValue());

        long alarmTime = calendar.getTimeInMillis() - System.currentTimeMillis();

        if(alarmTime >0)
        {
            timer = new Timer();
            timerTask =new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                }
            };
        }else{
            Toast.makeText(this, "Thời gian đã qua. Chọn thời điểm khác!", Toast.LENGTH_SHORT).show();
        }

        btnSetAlarm.setVisibility(View.GONE);
        btnStopAlarm.setVisibility(View.VISIBLE);
    }

    private void stopAlarm() {
        if(timerTask !=null){
        timerTask.cancel() ;
        }

        if(vibrator !=null){
            vibrator.cancel();
        }
        btnSetAlarm.setVisibility(View.VISIBLE);
        btnStopAlarm.setVisibility(View.GONE);

    }


}