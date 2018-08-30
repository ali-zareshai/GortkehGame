package com.kavireletronic.ali.gortkehgame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static maes.tech.intentanim.CustomIntent.customType;

public class SplashActivity extends AppCompatActivity {
    private ImageView icon_spalsh;
    private android.view.animation.Animation animFaz1;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        icon_spalsh=(ImageView)findViewById(R.id.icon_spalsh);

        animFaz1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_down);
        mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                icon_spalsh.startAnimation(animFaz1);
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, ListActivity.class);
                startActivity(i);
                customType(SplashActivity.this,"fadein-to-fadeout");
                finish();
            }
        }, 3500);

    }
}
