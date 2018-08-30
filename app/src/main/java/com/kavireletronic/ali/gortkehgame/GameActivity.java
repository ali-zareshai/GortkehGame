package com.kavireletronic.ali.gortkehgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Utils.FormatHelper;
import Utils.RandomNum;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView,javabTahih,mar_txt,emtiaz_txt;
    private List<String> ahdad=new ArrayList<>();
    private Timer timer;
    private TimerTask timerTask;
    private Button start,ok_javab,new_game;
    private Handler mHandler;
    private CardView javab;
    private EditText javabEdit;
    private android.view.animation.Animation animFaz1, animFaz2;
    private int count=0;
    private String getJavabFinal;
    private String level_,mar_,ahdad_,argame_;
    private SharedPreferences SP;
    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textView=(TextView) findViewById(R.id.numberimgage);
        start=(Button)findViewById(R.id.btn_spotify);
        javab=(CardView)findViewById(R.id.javab_card);
        javabEdit=(EditText)findViewById(R.id.javabedit);
        ok_javab=(Button)findViewById(R.id.btn_ok);
        new_game=(Button)findViewById(R.id.btn_new);
        javabTahih=(TextView)findViewById(R.id.javatahiehtxt);
        mar_txt=(TextView) findViewById(R.id.mar_txt);
        emtiaz_txt=(TextView)findViewById(R.id.emtiaz_txt);

        SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = SP.edit();


        /// get intent
        level_=getIntent().getExtras().getString("level").toString();
        mar_=getIntent().getExtras().getString("mar").toString();
        ahdad_=getIntent().getExtras().getString("ahdad").toString();
        argame_=getIntent().getExtras().getString("argam").toString();

        mar_txt.setText(FormatHelper.toPersianNumber( mar_+"مرحله:"));
        emtiaz_txt.setText(FormatHelper.toPersianNumber("امتیاز:"+String.valueOf(SP.getInt("emtiaz",0))));


        start.setOnClickListener(this);
        ok_javab.setOnClickListener(this);
        new_game.setOnClickListener(this);

        timer=new Timer();
        mHandler = new Handler();



    }
    private void setJavabTahih(String str){
        javabTahih.setText(FormatHelper.toPersianNumber("جواب صحیح: "+str));
    }

    private void setNumberToImage() {
        textView.setTextSize(130);
        ahdad= RandomNum.getInit(Integer.parseInt(argame_),Integer.parseInt(ahdad_),Integer.parseInt(level_));
        getJavabFinal=String.valueOf(RandomNum.getJavab());
        setJavabTahih(getJavabFinal);
        Log.e("ahdad",String.valueOf(ahdad));
        Log.e("javab:",getJavabFinal);
        loop();

    }

    private void setTextViewn(String n){
        textView.setText(FormatHelper.toPersianNumber(n));
    }
    private void loop(){
        timerTask=new TimerTask() {
            @Override
            public void run() {
                if (ahdad.size()<=count){
                    timerTask.cancel();
                    timer.cancel();
                    timer.purge();
                    getJavab();
                }else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setTextViewn(ahdad.get(count));
                            count++;
                        }
                    });


                }

            }
        };
        timer.schedule(timerTask,2500,1000);
    }

    private void getJavab() {
        animFaz1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.exit);
        mHandler.post(new Runnable() {
                @Override
                public void run() {
                    textView.startAnimation(animFaz1);
                    textView.setVisibility(View.INVISIBLE);
                }
        });




        animFaz2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                javab.startAnimation(animFaz2);
            }
        });


    }


    @Override
    public void onClick(View view) {
        if (view.getId()==start.getId()){
            start.setVisibility(View.GONE);
            textView.setTextSize(30);
            textView.setText(FormatHelper.toPersianNumber(getString(R.string.amade)));
            setNumberToImage();

        }else if (view.getId()==new_game.getId()){
            startnew();
        }else if (view.getId()==ok_javab.getId()){
            ok_javab.setEnabled(false);
            checkJavab();
        }
    }

    private void checkJavab() {
        String userJavab=javabEdit.getText().toString().trim();
        if (userJavab.equalsIgnoreCase(getJavabFinal)){
            javabIsTrue();
        }else {
            javabIsFalse();
        }
    }

    private void javabIsFalse() {
        MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.badjavab),MDToast.LENGTH_LONG,MDToast.TYPE_ERROR);
        mdToast.show();
        javabTahih.setVisibility(View.VISIBLE);
        updateEmtiaz(false);
    }

    private void updateEmtiaz(Boolean status) {
        if (status){
            if (SP.getInt("level_user",1)==Integer.parseInt(mar_)){
                int level=SP.getInt("level_user",1);
                level++;
                editor.putInt("level_user",level);
                int emtiaz=SP.getInt("emtiaz",0);
                emtiaz=emtiaz+10;
                editor.putInt("emtiaz",emtiaz);
                editor.apply();

            }
        }else {
            if (SP.getInt("level_user",1)==Integer.parseInt(mar_)){
                int emtiaz=SP.getInt("emtiaz",0);
                emtiaz=emtiaz-5;
                editor.putInt("emtiaz",emtiaz);
                editor.apply();

            }
        }
    }

    private void javabIsTrue() {
        MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.truejavab),MDToast.LENGTH_LONG,MDToast.TYPE_SUCCESS);
        mdToast.show();
        updateEmtiaz(true);
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
        finish();

    }

    private void startnew() {
        animFaz2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.exit);
        javab.startAnimation(animFaz2);


        animFaz1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                javab.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.startAnimation(animFaz1);
                start.setVisibility(View.VISIBLE);
            }
        });
        ahdad=null;
        ahdad=new ArrayList<>();
        textView.setTextSize(90);
        textView.setText(getString(R.string.start));
        getJavabFinal="";
        timer=new Timer();
        ok_javab.setEnabled(true);
        javabTahih.setVisibility(View.GONE);
        javabEdit.setText("");
        count=0;





    }
}
