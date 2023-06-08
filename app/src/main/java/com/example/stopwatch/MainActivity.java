package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnStop;
    Chronometer chronometer;
    ImageView Image;
    Animation animation;
    long pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.start);
        btnStop = findViewById(R.id.stop);
        chronometer = findViewById(R.id.chronometer);
        Image = findViewById(R.id.imageView);

        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotation);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image.startAnimation(animation);
                chronometer.setFormat("%s");
                chronometer.setBase(SystemClock.elapsedRealtime()-pause);
                chronometer.start();
                btnStart.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                pause = 0;
                animation.cancel();
                Image.setAnimation(animation);
                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
            }
        });
    }
}