package com.example.passwordgeneratorPrafull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        new Handler().postDelayed((Runnable) () -> {
            Intent toHome = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(toHome);
            finish();
        }, 1700);
    }
}