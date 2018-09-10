package com.kavireletronic.ali.gortkehgame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static maes.tech.intentanim.CustomIntent.customType;

public class SplashActivity extends AppCompatActivity {
    private ImageView icon_spalsh,logo;
    private android.view.animation.Animation animFaz1,animfadin;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        icon_spalsh=(ImageView)findViewById(R.id.icon_spalsh);
        logo=(ImageView)findViewById(R.id.icn1);

        animFaz1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_down);
        animfadin=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        animfadin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animFaz1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        logo.startAnimation(animfadin);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
        }, 5000);

    }
}
