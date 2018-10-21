package com.example.hw3_b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.notification.R;

public class SecondActivity extends AppCompatActivity {

    TextView tvNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvNotif = findViewById(R.id.tv_notif);
    }
}
