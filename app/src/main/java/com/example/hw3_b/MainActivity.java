package com.example.hw3_b;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notification.R;

public class MainActivity extends AppCompatActivity {

    private EditText etMin;
    private EditText etSec;
    private TextView tvLeft;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMin =  findViewById(R.id.et_time_minutes);
        etSec = findViewById(R.id.et_time_sec);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        tvLeft = findViewById(R.id.tv_timer);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer();
                tvLeft.setText("");
            }
        });
    }


    private void start() {
        long timeOnTimer = Integer.parseInt(etMin.getText().toString()) * 60000 +
                Integer.parseInt(etSec.getText().toString()) * 1000;
        start(timeOnTimer);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intentTimer = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast
                (this, 0, intentTimer, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + timeOnTimer, pendingIntent);
    }

    private void start(final long timeOnTimer) {
        timer = new CountDownTimer(timeOnTimer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvLeft.setText("" + millisUntilFinished / 60000 + " min " + (millisUntilFinished % 60000) / 1000 + " sec"
                + " left");
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Yeeeey", Toast.LENGTH_LONG)
                        .show();
            }
        } .start();
    }

    private void cancelTimer() {
        alarmManager.cancel(pendingIntent);
        timer.cancel();
    }
}
