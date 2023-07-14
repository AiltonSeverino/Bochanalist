package com.example.obochanalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();

        handle.postDelayed(this::mostrarlogin, 3000);

    }

    private void mostrarlogin(){
        Intent home = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(home);
        finish();
    };
}