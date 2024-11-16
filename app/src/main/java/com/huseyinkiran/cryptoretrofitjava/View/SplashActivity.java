package com.huseyinkiran.cryptoretrofitjava.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import com.huseyinkiran.cryptoretrofitjava.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        videoView = findViewById(R.id.logoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.animated_logo_gray);
        videoView.setVideoURI(videoUri);
        videoView.start();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 2000);

    }
}