package com.example.laundrymanagementsystem;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if (sharedPreferences.getString("Login", "").equals("true")) {
                        if (sharedPreferences.getString("User", "").equals("admin")) {
                            Intent intent = new Intent(SplashActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (sharedPreferences.getString("User", "").equals("user")) {
                            Intent intent = new Intent(SplashActivity.this, UserHomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (sharedPreferences.getString("User", "").equals("vendor")) {
                            Intent intent = new Intent(SplashActivity.this, VendorHomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {

                }
            }
        });
        thread.start();


    }
}