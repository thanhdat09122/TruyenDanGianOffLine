package com.example.mypc.truyenoffline.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mypc.truyenoffline.R;

public class SpashActivity extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SpashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

//    @Override
//    protected void onPause() {
//        mHandler.removeCallbacksAndMessages(null);
//        super.onPause();
//    }
}
