package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvThread1, tvThread2, tvThread3;
    private Button btnThread1, btnThread2, btnThread3;

    private boolean isThread1Running = true;
    private boolean isThread2Running = true;
    private boolean isThread3Running = true;

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvThread1 = findViewById(R.id.tvThread1);
        tvThread2 = findViewById(R.id.tvThread2);
        tvThread3 = findViewById(R.id.tvThread3);

        btnThread1 = findViewById(R.id.btnThread1);
        btnThread2 = findViewById(R.id.btnThread2);
        btnThread3 = findViewById(R.id.btnThread3);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        tvThread1.setText("Thread 1:" +msg.arg1);
                        break;
                    case 2:
                        tvThread2.setText("Thread 2:" +msg.arg1);
                        break;
                        case 3:
                            tvThread3.setText("Thread 3:" +msg.arg1);
                            break;


                }
            }

        };

        new Thread(new Runnable()
        {
            @Override
            public void run(){
                Random random = new Random();
                while(true){
                    if(isThread1Running){
                        int num = random.nextInt(51)+50;
                        Message msg = handler.obtainMessage(1);
                        msg.arg1 = num;
                        handler.sendMessage(msg);
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

        }).start();

        new Thread(new Runnable() {
            int oddNumber = 1;
            @Override
            public void run() {
                while(true){
                    if(isThread2Running){
                        Message msg = handler.obtainMessage(2);
                        msg.arg1 = oddNumber;
                        handler.sendMessage(msg);
                        oddNumber +=2;
                        try{
                            Thread.sleep(2500);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            int number = 0;
            @Override
            public void run() {
                while (true){
                if (isThread3Running) {
                    Message msg = handler.obtainMessage(3);
                    msg.arg1 = number;
                    handler.sendMessage(msg);
                    number++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
            }
        }).start();

        btnThread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread1Running = !isThread1Running;
            }
        });

        btnThread2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                isThread2Running = !isThread2Running;
            }
        });

        btnThread3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread3Running = !isThread3Running;
            }
        });
    }
}